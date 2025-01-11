package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.option.OptionResponse;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.dto.variant.VariantResponse;
import com.sapo.store_management.model.*;
import com.sapo.store_management.repository.BrandRepo;
import com.sapo.store_management.repository.CategoryRepo;
import com.sapo.store_management.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TagRepo tagRepo;

    public static ProductResponse convertProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setCode(product.getCode());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCapital_price(product.getCapital_price());
        response.setImage(product.getImage());
        response.setStatus(product.isStatus());
        response.setCreated_at(product.getCreated_at());
        response.setUpdated_at(product.getUpdated_at());

        // Set brand name
        response.setBrand_name(product.getBrand() != null ? product.getBrand().getName() : "");

        // Set category name
        response.setCategory_name(product.getCategory() != null ? product.getCategory().getName() : "");

        // Set tag name
        response.setTag_name(product.getTag() != null ? product.getTag().getName() : "");

        // Convert Option list to OptionResponse list if options are not empty
        List<OptionResponse> optionResponses = (product.getOptions() != null && !product.getOptions().isEmpty()) ?
                product.getOptions().stream()
                        .map(option -> OptionResponse.builder()
                                .id(option.getId())
                                .name(option.getName())
                                .values(option.getValues().stream()
                                        .map(value -> value.getName())  // Map Value names to a list of strings
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList())
                :
                List.of();
        response.setOptions(optionResponses);

        // Convert Variant list to VariantResponse list if variants are not empty
        List<VariantResponse> variantResponses = (product.getVariants() != null && !product.getVariants().isEmpty()) ?
                product.getVariants().stream()
                        .map(variant -> VariantResponse.builder()
                                .variantDescription(String.join("-", variant.getValues())) // Combine option values (e.g., "S-Đỏ")
                                .price(variant.getPrice())
                                .build())
                        .collect(Collectors.toList())
                :
                List.of();
        response.setVariants(variantResponses);

        return response;
    }

    public Product convertProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCapital_price(productRequest.getCapital_price());
        product.setImage(productRequest.getImage());
        product.setStatus(productRequest.isStatus());
        product.setCreated_at(LocalDateTime.now());
        product.setUpdated_at(LocalDateTime.now());

        if (productRequest.getBrand() != null) {
            Brand brand = brandRepo.findById(productRequest.getBrand())
                    .orElse(null);
            product.setBrand(brand);
        }
        if (productRequest.getCategory() != null) {
            Category category = categoryRepo.findById(productRequest.getCategory())
                    .orElse(null);
            product.setCategory(category);
        }
        if (productRequest.getTag() != null) {
            Tag tag = tagRepo.findById(productRequest.getTag())
                    .orElse(null);
            product.setTag(tag);
        }

        return product;
    }
}
