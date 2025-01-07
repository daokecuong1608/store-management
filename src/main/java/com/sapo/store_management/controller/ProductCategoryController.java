package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductCategoryRequest;
import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.service.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productcategory")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("")
    public ResponseEntity<Page<ProductCategory>> getAllProductCategory(@RequestParam int page,
                                                                       @RequestParam int size,
                                                                       @RequestParam(defaultValue = "id") String sortBy) {
        Page<ProductCategory> productCategory = productCategoryService.getAllPC(page, size, sortBy);
        return ResponseEntity.ok(productCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryByID(@PathVariable int id) {
        ProductCategory category = productCategoryService.findById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<ProductCategory> insertProductCategory(@Valid @RequestBody ProductCategory productCategory) {
        ProductCategory result = productCategoryService.insertPC(productCategory);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductCategoryRequest> updateProductCategory(@Valid @PathVariable int id, @RequestBody ProductCategoryRequest productCategoryRequest) {
        ProductCategoryRequest request = productCategoryService.updatePC(id, productCategoryRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable int id) {
        productCategoryService.deletePC(id);
        return ResponseEntity.ok().build();
    }
}
