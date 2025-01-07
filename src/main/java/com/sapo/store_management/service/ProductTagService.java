package com.sapo.store_management.service;

import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.repository.ProductTagRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTagService {
    private final ProductTagRepo productTagRepo;
    public ProductTagService(ProductTagRepo productTagRepo) {
        this.productTagRepo = productTagRepo;
    }

    public List<ProductTag> getAllProductTags() {return productTagRepo.findAll();}

    public ProductTag getProductTagById(int id) {return productTagRepo.findById(id).get();}

    public void insertProductTag(ProductTag productTag) {productTagRepo.save(productTag);}

    public void deleteProductTag(ProductTag productTag) {productTagRepo.delete(productTag);}
}
