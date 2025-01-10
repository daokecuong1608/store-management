package com.sapo.store_management.controller;

import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<ProductResponse>> getlAllProducts(@RequestParam int page,
                                                                 @RequestParam int size,
                                                                 @RequestParam(defaultValue = "id") String sortBy) {
        Page<ProductResponse> product = productService.getAllProductResponse(page, size, sortBy);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse product = productService.getProductResponseByID(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> insertProduct(@RequestBody ProductRequest product) {
        ProductResponse insert = productService.createProductResponse(product);
        if (product != null) {
            return ResponseEntity.ok(insert);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @PathVariable Integer id, @RequestBody ProductRequest productDTO) {

        ProductResponse dto = productService.updateProductResponse(id, productDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductResponse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-tag")
    public ResponseEntity<List<ProductResponse>> getProductsByTagName(@RequestParam String tagName) {
        List<ProductResponse> products = productService.getProductsByTagName(tagName);
        return ResponseEntity.ok(products);
    }

}
