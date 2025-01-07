package com.sapo.store_management.controller;

import com.sapo.store_management.dto.CategoryRequest;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
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

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryRequest> updateCategory(@Valid @PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        CategoryRequest request = categoryService.updateCategory(id, categoryRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete")
    public void deleteCategory(@RequestBody Category category) {categoryService.deleteCategory(category);}
}
