package com.sapo.store_management.controller;

import com.sapo.store_management.dto.category.CategoryRequest;
import com.sapo.store_management.dto.category.CategoryResponse;
import com.sapo.store_management.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<CategoryResponse> categories = categoryService.getAllCategory(page, size, sortBy);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> insertCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse insertCategory = categoryService.insertCategory(categoryRequest);
        return ResponseEntity.ok(insertCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse request = categoryService.updateCategory(id, categoryRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
