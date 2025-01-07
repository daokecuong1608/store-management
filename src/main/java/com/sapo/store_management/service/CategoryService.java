package com.sapo.store_management.service;

import com.sapo.store_management.dto.CategoryRequest;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.repository.CategoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Page<Category> findAllCategories(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Category> result = categoryRepo.findAll(pageable);
        return result;
    }

    public Category findById(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Could not find category"));

        return category;
    }

    public Category saveCategory(Category category) {
        Category category_1 = categoryRepo.save(category);
        return category_1;
    }

    public void deleteCategory(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Category not"));

        categoryRepo.delete(category);
    }

    public CategoryRequest updateCategory(int id, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(categoryRequest.getName());
        category.setUpdated_at(new Date(System.currentTimeMillis()));

        Category update = categoryRepo.save(category);

        return new CategoryRequest(update.getId(), update.getName());
    }
}
