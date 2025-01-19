package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.image.ImageRequest;
import com.sapo.store_management.dto.option.OptionRequest;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.dto.variant.VariantResponse;
import com.sapo.store_management.mapper.ProductMapper;
import com.sapo.store_management.model.*;
import com.sapo.store_management.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final VariantRepo variantRepo;
    private final BrandRepo brandRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;
    private final ImageRepo imageRepo;
    private final ValueRepo valueRepo;
    private final OptionRepo optionRepo;

    public ProductServiceImpl(ProductRepo productRepo, VariantRepo variantRepo, BrandRepo brandRepo, CategoryRepo categoryRepo, TagRepo tagRepo, ImageRepo imageRepo, ValueRepo valueRepo, OptionRepo optionRepo) {
        this.productRepo = productRepo;
        this.variantRepo = variantRepo;
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;
        this.imageRepo = imageRepo;
        this.valueRepo = valueRepo;
        this.optionRepo = optionRepo;
    }

    @Override
    public Page<ProductResponse> getAllProductResponse(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Product> response = productRepo.findAll(pageable);
        return response.map(ProductMapper::convertProductResponse);
    }

    @Override
    public ProductResponse getProductResponseByID(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        ProductResponse productResponse = ProductMapper.convertProductResponse(product);
        return productResponse;
    }

    @Override
    public ProductResponse createProductResponse(ProductRequest productRequest) {
        Product product = productMapper.convertProduct(productRequest);
        // Kiểm tra và xử lý danh sách ảnh được gửi trong ProductRequest
        List<ImageRequest> imageRequests = productRequest.getImages();
        if (imageRequests != null && !imageRequests.isEmpty()) {
            List<Image> images = new ArrayList<>();
            for (ImageRequest imageRequest : imageRequests) {
                Image image = new Image();
                image.setImageUrl(imageRequest.getUrl());
                image.setProduct(product); // Gắn sản phẩm cho ảnh
                images.add(image);
            }
            product.setImages(images); // Thiết lập danh sách ảnh cho sản phẩm
        }
        productRepo.save(product);
        generateVariantsForProduct(product, productRequest.getOptions());
        return mapProductToResponse(product);

    }

    @Override
    public ProductResponse updateProductResponse(Integer id, ProductRequest productRequest) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find product"));

        // Update basic fields (code, name, description, etc.)
        updateBasicFields(product, productRequest);

        // Update brand
        updateBrand(product, productRequest);

        // Update categories
        updateCategories(product, productRequest);

        // Update tags
        updateTags(product, productRequest);

        // Update options and values
        updateOptionsAndValues(product, productRequest);

        // Update variants based on new options
        updateVariants(product, productRequest);

        // Update images with orphan removal
        updateImages(product, productRequest);

        // Save the product with all updates
        productRepo.save(product);

        // Map and return response
        return mapProductToResponse(product);
    }


    @Override
    public void deleteProductResponse(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        productRepo.delete(product);
    }

    @Override
    public List<ProductResponse> getProductsByTagName(String tagName) {
        List<Product> product = productRepo.findByTagName(tagName);
        return product.stream()
                .map(ProductMapper::convertProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can not find product"));
        return product;
    }


    @Override
    public Page<ProductResponse> getProductByName(String productName, int page, int size, String sortBy) {
        String formatProductName = "%" + productName.toLowerCase() + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Product> response = productRepo.findByProductName(formatProductName, pageable);
        return response.map(ProductMapper::convertProductResponse);
    }

    /////////////////////////////////////////////

    private void generateVariantsForProduct(Product product, List<OptionRequest> optionRequests) {
        if (optionRequests == null || optionRequests.isEmpty()) {
            product.setOptions(new ArrayList<>());
            product.setVariants(new ArrayList<>());
            return;
        }
        List<Option> options = optionRequests.stream().map(optionRequest -> {
            Option option = new Option();
            option.setName(optionRequest.getName());
            option.setProduct(product);
            List<Value> values = optionRequest.getValues().stream().map(valueName -> {
                Value value = new Value();
                value.setName(valueName);
                value.setOption(option);
                return value;
            }).collect(Collectors.toList());
            option.setValues(values);
            return option;
        }).collect(Collectors.toList());
        product.setOptions(options);
        List<List<String>> combinations = cartesianProduct(options.stream()
                .map(option -> option.getValues().stream()
                        .map(Value::getName)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));

        List<Variant> variants = combinations.stream().map(combination -> {
            Variant variant = new Variant();
            variant.setProduct(product);
            variant.setValues(combination);
            variant.setPrice(product.getPrice());
            return variant;
        }).collect(Collectors.toList());

        product.setVariants(variants);
        variantRepo.saveAll(variants);
    }

    private List<List<String>> cartesianProduct(List<List<String>> lists) {
        List<List<String>> result = new ArrayList<>();
        if (lists.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }
        List<String> firstList = lists.get(0);
        List<List<String>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
        for (String value : firstList) {
            for (List<String> combination : remainingLists) {
                List<String> newCombination = new ArrayList<>(combination);
                newCombination.add(0, value);
                result.add(newCombination);
            }
        }
        return result;
    }

    private void updateBasicFields(Product product, ProductRequest productRequest) {
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCapital_price(productRequest.getCapital_price());
        product.setStatus(productRequest.isStatus());
    }

    private void updateBrand(Product product, ProductRequest productRequest) {
        if (productRequest.getBrand() != null) {
            Brand brand = brandRepo.findById(productRequest.getBrand())
                    .orElseThrow(() -> new RuntimeException("Could not find"));
            product.setBrand(brand);
        } else {
            product.setBrand(null);
        }
    }

    private void updateCategories(Product product, ProductRequest productRequest) {
        List<Category> categories = productRequest.getCategories() == null ? null :
                categoryRepo.findAllById(productRequest.getCategories());
        product.setCategories(categories);
    }

    private void updateTags(Product product, ProductRequest productRequest) {
        List<Tag> tags = productRequest.getTags() == null ? null :
                tagRepo.findAllById(productRequest.getTags());
        product.setTags(tags);
    }

    private void updateOptionsAndValues(Product product, ProductRequest productRequest) {
        if (productRequest.getOptions() != null) {
            List<Option> newOptions = productRequest.getOptions().stream().map(optionRequest -> {
                Option option = new Option();
                option.setName(optionRequest.getName());
                option.setProduct(product);
                List<Value> values = optionRequest.getValues().stream().map(valueName -> {
                    Value value = new Value();
                    value.setName(valueName);
                    value.setOption(option);
                    return value;
                }).collect(Collectors.toList());
                option.setValues(values);
                return option;
            }).collect(Collectors.toList());
            // Clear and update options
            product.getOptions().clear();
            product.getOptions().addAll(newOptions);
        }
    }

    private void updateVariants(Product product, ProductRequest productRequest) {
        // If there are options, generate new variants based on them
        if (productRequest.getOptions() != null && !productRequest.getOptions().isEmpty()) {
            List<List<String>> allOptionValues = product.getOptions().stream()
                    .map(option -> option.getValues().stream().map(Value::getName).toList())
                    .toList();

            List<List<String>> combinations = cartesianProduct(allOptionValues);

            List<Variant> newVariants = combinations.stream().map(combination -> {
                Variant variant = new Variant();
                variant.setProduct(product);
                variant.setValues(combination);
                variant.setPrice(product.getPrice());
                return variant;
            }).collect(Collectors.toList());

            // Remove old variants and add new ones
            product.getVariants().clear();
            product.getVariants().addAll(newVariants);
            variantRepo.saveAll(newVariants);
        }
    }

    private void updateImages(Product product, ProductRequest productRequest) {
        if (productRequest.getImages() != null) {
            List<Image> newImages = productRequest.getImages().stream()
                    .map(imageRequest -> {
                        Image image = new Image();
                        image.setProduct(product);
                        image.setImageUrl(imageRequest.getUrl());
                        return image;
                    }).collect(Collectors.toList());

            // Explicitly delete orphaned images (images that are no longer in the request)
            List<Image> orphanedImages = product.getImages().stream()
                    .filter(image -> !newImages.contains(image))
                    .collect(Collectors.toList());
            imageRepo.deleteAll(orphanedImages);
            // Clear existing images and add the new ones
            product.getImages().clear();
            product.getImages().addAll(newImages);
        }
    }


    private ProductResponse mapProductToResponse(Product product) {
        ProductResponse response = ProductMapper.convertProductResponse(product);
        response.setVariants(product.getVariants().stream()
                .map(variant -> VariantResponse.builder()
                        .variantDescription(String.join("-", variant.getValues()))
                        .price(variant.getPrice())
                        .build())
                .collect(Collectors.toList()));
        return response;
    }
}