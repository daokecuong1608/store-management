package com.sapo.store_management.controller;

import com.sapo.store_management.model.ProductCategory;
import com.sapo.store_management.service.ProductCategoryService;
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
    public void insertProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.insertPC(productCategory);
    }

    @DeleteMapping("/delete")
    public void deleteProductCategory(@RequestBody ProductCategory productCategory) {productCategoryService.deletePC(productCategory);}
}
