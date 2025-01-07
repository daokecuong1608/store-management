package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductDTO;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Product>> getlAllProducts(@RequestParam int page,
                                                         @RequestParam int size,
                                                         @RequestParam(defaultValue = "id") String sortBy) {
        Page<Product> product = productService.getAllProducts(page, size, sortBy);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product insert = productService.saveProduct(product);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable int id, @RequestBody ProductDTO productDTO) {

        ProductDTO dto = productService.updateProduct(id, productDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
