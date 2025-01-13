package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.option.OptionRequest;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.dto.variant.VariantResponse;
import com.sapo.store_management.mapper.ProductMapper;
import com.sapo.store_management.model.*;
import com.sapo.store_management.repository.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public ProductServiceImpl(ProductRepo productRepo, VariantRepo variantRepo, BrandRepo brandRepo, CategoryRepo categoryRepo, TagRepo tagRepo) {
        this.productRepo = productRepo;
        this.variantRepo = variantRepo;
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;
    }

    @Override
    public Page<ProductResponse> getAllProductResponse(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
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
        product = productRepo.save(product);
        return ProductMapper.convertProductResponse(product);
    }

    @Override
    public ProductResponse updateProductResponse(Integer id, ProductRequest productRequest) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCapital_price(productRequest.getCapital_price());
        product.setImage(productRequest.getImage());
        product.setStatus(productRequest.isStatus());
        if (productRequest.getBrand() != null) {
            Brand brand = brandRepo.findById(productRequest.getBrand()).orElseThrow(null);
            product.setBrand(brand);
        }
        if (productRequest.getCategories() != null && !productRequest.getCategories().isEmpty()) {
            List<Category> categories = categoryRepo.findAllById(productRequest.getCategories());
            if (categories != null && !categories.isEmpty()) {
                product.setCategories(categories);
            } else {
                product.setCategories(null);
            }
        }
        if (productRequest.getTags() != null && !productRequest.getTags().isEmpty()){
            List<Tag> tags = tagRepo.findAllById(productRequest.getTags());
            if (tags!= null &&!tags.isEmpty()) {
                product.setTags(tags);
            } else {
                product.setTags(null);
            }
        }
        // Kiểm tra và xử lý các Option
        List<OptionRequest> inputOptions = productRequest.getOptions();
        if (inputOptions != null && !inputOptions.isEmpty()) {
            // Nếu có Option mới được truyền vào, xóa các Option cũ và cập nhật lại
            product.setOptions(new ArrayList<>());
            List<Option> updatedOptions = new ArrayList<>();
            for (OptionRequest inputOption : inputOptions) {
                Option option = new Option();
                option.setName(inputOption.getName());
                option.setProduct(product);
                List<Value> updateValues = new ArrayList<>();
                for (String valueName : inputOption.getValues()) {
                    Value value = new Value();
                    value.setName(valueName);
                    value.setOption(option);
                    updateValues.add(value);
                }
                option.setValues(updateValues);
                updatedOptions.add(option);
            }
            // Cập nhật các Option mới cho sản phẩm
            product.setOptions(updatedOptions);
        }
        // Lưu thông tin sản phẩm với các Option đã cập nhật
        productRepo.save(product);
        // Nếu có Option mới, tạo các Variant dựa trên Option và Value
        List<Variant> variants = new ArrayList<>();
        if (inputOptions != null && !inputOptions.isEmpty()) {
            // Tạo các Variant chỉ khi có Option
            List<List<String>> allOptionValues = product.getOptions()
                    .stream()
                    .map(o -> o.getValues().stream().map(Value::getName)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            List<List<String>> combinations = cartesianProduct(allOptionValues);
            for (List<String> combination : combinations) {
                Variant variant = Variant.builder()
                        .product(product)
                        .values(combination)
                        .price(product.getPrice())
                        .build();
                variants.add(variant);
            }
            // Lưu tất cả các variants vào cơ sở dữ liệu
            variantRepo.saveAll(variants);
        }

        // Chuyển đổi Product thành ProductResponse và thiết lập variants
        ProductResponse response = ProductMapper.convertProductResponse(product);
        response.setVariants(variants.stream()
                .map(variant -> VariantResponse.builder()
                        .variantDescription(String.join("-", variant.getValues()))  // Combine option values (e.g., "S-Đỏ")
                        .price(variant.getPrice())
                        .build())
                .collect(Collectors.toList()));

        return response;

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
    public ProductResponse generateVariantsForProduct(Product product, List<OptionRequest> inputOptions) {
        // Tạo danh sách Option từ dữ liệu người dùng nhập vào
        List<Option> options = new ArrayList<>();
        for (OptionRequest input : inputOptions) {
            Option option = new Option();
            option.setName(input.getName());
            option.setProduct(product);

            //Tạo value cho option
            List<Value> values = new ArrayList<>();
            for (String valueName : input.getValues()) {
                Value value = new Value();
                value.setName(valueName);
                value.setOption(option);  // Liên kết Value với Option
                values.add(value);
            }
            option.setValues(values);
            options.add(option);
        }
        // Gán các Option mới vào Product
        product.setOptions(options);

        // Nếu không có tùy chọn thì không sinh Variant
        if (options == null || options.isEmpty()) {
            return ProductMapper.convertProductResponse(product);
        }

        // Lấy danh sách các giá trị cho mỗi Option
        List<List<String>> allOptionValues = new ArrayList<>();
        for (Option option : options) {
            List<String> values = option.getValues().stream()
                    .map(Value::getName)
                    .collect(Collectors.toList());
            allOptionValues.add(values);
        }
        // Kết hợp các giá trị để sinh Variant (Cartesian Product)
        List<List<String>> combinations = cartesianProduct(allOptionValues);

        // Tạo danh sách Variant từ kết hợp các giá trị
        List<Variant> variants = new ArrayList<>();
        for (List<String> combination : combinations) {
            Variant variant = Variant.builder()
                    .product(product)
                    .values(combination)  // Lưu danh sách giá trị
                    .price(product.getPrice())  // Áp dụng giá của sản phẩm
                    .build();
            variants.add(variant);
        }
        // Lưu các Variant vào cơ sở dữ liệu
        variantRepo.saveAll(variants);

        // Chuyển đổi Product thành ProductResponse và thiết lập variants
        ProductResponse response = ProductMapper.convertProductResponse(product);
        response.setVariants(variants.stream()
                .map(variant -> VariantResponse.builder()
                        .variantDescription(String.join("-", variant.getValues()))  // Combine option values (e.g., "S-Đỏ")
                        .price(variant.getPrice())
                        .build())
                .collect(Collectors.toList()));

        return response;
    }

    public List<List<String>> cartesianProduct(List<List<String>> lists) {
        List<List<String>> result = new ArrayList<>();
        if (lists.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }
        List<String> firstList = lists.get(0);
        //tạo ra tất cả các kết hợp có thể có của danh sách
        List<List<String>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));

        for (String value : firstList) {
            for (List<String> combination : remainingLists) {
                List<String> newCombination = new ArrayList<>();
                newCombination.add(value);
                newCombination.addAll(combination);
                result.add(newCombination);
            }
        }
        return result;
    }

    @Override
    public Page<ProductResponse> getProductByName(String productName, int page, int size, String sortBy) {
        String formatProductName = "%" + productName.toLowerCase() + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Product> response = productRepo.findByProductName(formatProductName, pageable);
        return response.map(ProductMapper::convertProductResponse);
    }
}
