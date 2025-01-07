package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductCategoryRequest;
import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.service.ProductCategoryService;
import jakarta.validation.Valid;
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
    public List<ProductCategory> getAllProductCategory() {return productCategoryService.getAllPC();}

    @GetMapping("/{id}")
    public ProductCategory getProductCategoryByID(@PathVariable int id) {return productCategoryService.findById(id);}

    @PostMapping("/insert")
    public void insertProductCategory(@RequestBody ProductCategory productCategory) {productCategoryService.insertPC(productCategory);}

    @PutMapping("update/{id}")
    public ResponseEntity<ProductCategoryRequest> updateProductCategory(@Valid @PathVariable int id, @RequestBody ProductCategoryRequest productCategoryRequest) {
        ProductCategoryRequest request = productCategoryService.updatePC(id, productCategoryRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteProductCategory(@RequestBody ProductCategory productCategory) {productCategoryService.deletePC(productCategory);}
}
