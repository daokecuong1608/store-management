package com.sapo.store_management.controller;

import com.sapo.store_management.dto.CategoryRequest;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Category>> getAllCategories(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Category> categories = categoryService.findAllCategories(page, size, sortBy);
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/insert")
    public ResponseEntity<Category> insertCategory(@Valid @RequestBody Category category) {
        Category insertCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(insertCategory);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryRequest> updateCategory(@Valid @PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        CategoryRequest request = categoryService.updateCategory(id, categoryRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
