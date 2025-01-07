package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductDTO;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public void insertProduct(@RequestBody Product product)
    {productService.saveProduct(product);}



    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable int id, @RequestBody ProductDTO productDTO) {

        ProductDTO dto = productService.updateProduct(id, productDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }




    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody Product product) {productService.deleteProduct(product);}
}
