package com.sapo.store_management.controller;

import com.sapo.store_management.model.Category;
import com.sapo.store_management.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategories() {return categoryService.findAllCategories();}

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {return categoryService.findById(id);}

    @PostMapping("/insert")
    public void insertCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category) {categoryService.saveCategory(category);}

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestBody Category category) {categoryService.deleteCategory(category);}
}
