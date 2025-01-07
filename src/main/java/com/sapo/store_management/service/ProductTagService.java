package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductTagRequest;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.repository.ProductTagRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductTagService {
    private final ProductTagRepo productTagRepo;

    public ProductTagService(ProductTagRepo productTagRepo) {
        this.productTagRepo = productTagRepo;
    }

    public Page<ProductTag> getAllProductTags(int page , int size , String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<ProductTag> result = productTagRepo.findAll(pageable);
        return result;
    }

    public ProductTag getProductTagById(int id) {
        ProductTag productTag = productTagRepo.findById(id).orElseThrow(() -> new RuntimeException("Product tag not found"));
        return productTag;
    }

    public ProductTag insertProductTag(ProductTag productTag) {
ProductTag tag = productTagRepo.save(productTag);
        return tag;
    }

    public void deleteProductTag(int id) {
        ProductTag productTag = productTagRepo.findById(id).orElseThrow(() -> new RuntimeException("Product tag not found"));

        productTagRepo.delete(productTag);
    }

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
