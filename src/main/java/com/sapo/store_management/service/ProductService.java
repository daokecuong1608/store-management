package com.sapo.store_management.service;

import com.sapo.store_management.model.Product;
import com.sapo.store_management.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {return productRepo.findAll();}

    public Product getProductById(int id) {return productRepo.getReferenceById(id);}

    public void saveProduct(Product product) {productRepo.save(product);}

    public void deleteProduct(Product product) {productRepo.delete(product);}
}
