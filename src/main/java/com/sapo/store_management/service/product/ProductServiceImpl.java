package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.option.OptionRequest;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.dto.variant.VariantResponse;
import com.sapo.store_management.mapper.ProductMapper;
import com.sapo.store_management.model.Option;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Value;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.repository.ProductRepo;
import com.sapo.store_management.repository.VariantRepo;
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

    public ProductServiceImpl(ProductRepo productRepo, VariantRepo variantRepo) {
        this.productRepo = productRepo;
        this.variantRepo = variantRepo;
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
        Product update = productMapper.convertProduct(productRequest);
        update = productRepo.save(update);
        return ProductMapper.convertProductResponse(update);

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

}
