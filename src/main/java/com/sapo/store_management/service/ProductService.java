package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductDTO;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Product> result = productRepo.findAll(pageable);
        return result;

    }

    public Product getProductById(int id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }

    public Product saveProduct(Product product) {
        Product insert = productRepo.save(product);
        return insert;
    }

    public void deleteProduct(int id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepo.delete(product);
    }


    public ProductDTO updateProduct(int product_id, ProductDTO productDTO) {
        Product product = productRepo.findById(product_id)
                .orElseThrow(() -> new RuntimeException("Not find"));

        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCapital_price(productDTO.getCapital_price());
        product.setImage(productDTO.getImage());
//        product.setStatus(.getStatus());
//        product.setUpdated_date(new Date(System.currentTimeMillis()));

        Product update = productRepo.save(product);

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCode(update.getCode());
        dto.setName(update.getName());
        dto.setDescription(update.getDescription());
        dto.setPrice(update.getPrice());
        dto.setCapital_price(update.getCapital_price());
        dto.setImage(update.getImage());
//        dto.setStatus(update.getStatus());
        return dto;
    }


}
