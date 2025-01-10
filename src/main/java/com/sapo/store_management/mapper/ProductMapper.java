package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.model.Brand;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.repository.BrandRepo;
import com.sapo.store_management.repository.CategoryRepo;
import com.sapo.store_management.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

        return response;
    }

    public  Product convertProduct(ProductRequest productRequest) {
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
