package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductTagRequest;
import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.repository.ProductTagRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public ProductTagRequest updateProductTag(int id, ProductTagRequest productTagRequest) {
        ProductTag productTag = productTagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Tag not found"));

        productTag.setProduct_id(productTagRequest.getProduct_id());
        productTag.setTag_id(productTagRequest.getTag_id());
        productTag.setUpdated_at(new Date(System.currentTimeMillis()));

        ProductTag update = productTagRepo.save(productTag);

        return new ProductTagRequest(
                update.getProduct_id(),
                update.getTag_id(),
                update.getProduct_id());
    }
}
