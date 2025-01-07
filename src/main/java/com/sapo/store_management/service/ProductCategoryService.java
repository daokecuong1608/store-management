package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductCategoryRequest;
import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.repository.ProductCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;
    public ProductCategoryService(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    public List<ProductCategory> getAllPC() {return productCategoryRepo.findAll();}

    public ProductCategory findById(int id) {return productCategoryRepo.getReferenceById(id);}

    public void insertPC(ProductCategory productCategory) {productCategoryRepo.save(productCategory);}

    public void deletePC(ProductCategory productCategory) {productCategoryRepo.delete(productCategory);}

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
