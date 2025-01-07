package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductCategoryRequest;
import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.repository.ProductCategoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategoryService(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    public Page<ProductCategory> getAllPC(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<ProductCategory> result = productCategoryRepo.findAll(pageable);
        return result;
    }

    public ProductCategory findById(int id) {
        ProductCategory productCategory = productCategoryRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("ProductCategory not found"));
        return productCategory;
    }

    public ProductCategory insertPC(ProductCategory productCategory) {
        ProductCategory inProductCategory = productCategoryRepo.save(productCategory);
        return inProductCategory;
    }

    public void deletePC(int id) {
        ProductCategory productCategory = productCategoryRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("ProductCategory not found"));
        productCategoryRepo.delete(productCategory);
    }

    public ProductCategoryRequest updatePC(int id, ProductCategoryRequest productCategoryRequest) {
        ProductCategory productCategory = productCategoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Category Not Found"));

        productCategory.setCategory_id(productCategoryRequest.getCategory_id());
        productCategory.setProduct_id(productCategoryRequest.getProduct_id());
        productCategory.setUpdated_at(new Date(System.currentTimeMillis()));

        ProductCategory update = productCategoryRepo.save(productCategory);
        return new ProductCategoryRequest(
                update.getCategory_id(),
                update.getProduct_id(),
                update.getCategory_id());
    }
}
