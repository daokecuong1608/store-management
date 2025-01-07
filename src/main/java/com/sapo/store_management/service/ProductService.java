package com.sapo.store_management.service;

import com.sapo.store_management.dto.ProductDTO;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
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

//    private String code;
//
//    private String name;
//
//    private String description;
//
//    private int price;
//
//    private int capital_price;
//
//    private String image;
//
//    private String status;

public ProductDTO updateProduct(int product_id  , ProductDTO productDTO){
        Product product = productRepo.findById(product_id)
                .orElseThrow(() -> new RuntimeException("Not find"));

        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCapital_price(productDTO.getCapital_price());
        product.setImage(productDTO.getImage());
        product.setStatus(productDTO.getStatus());
        product.setUpdated_date(new Date(System.currentTimeMillis()));

        Product update = productRepo.save(product);

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCode(update.getCode());
        dto.setName(update.getName());
        dto.setDescription(update.getDescription());
        dto.setPrice(update.getPrice());
        dto.setCapital_price(update.getCapital_price());
        dto.setImage(update.getImage());
        dto.setStatus(update.getStatus());
        return dto;
}


}
