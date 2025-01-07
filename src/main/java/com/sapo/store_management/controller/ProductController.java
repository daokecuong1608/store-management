package com.sapo.store_management.controller;

import com.sapo.store_management.model.Product;
import com.sapo.store_management.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getlAllProducts() {return productService.getAllProducts();}

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {return productService.getProductById(id);}

    @PostMapping("/insert")
    public void insertProduct(@RequestBody Product product) {productService.saveProduct(product);}

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {productService.saveProduct(product);}

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody Product product) {productService.deleteProduct(product);}
}
