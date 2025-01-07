package com.sapo.store_management.service;

import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.repository.ProductCategoryRepo;
import org.springframework.stereotype.Service;

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
}
