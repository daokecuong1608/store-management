package com.sapo.store_management.service.category;


import com.sapo.store_management.dto.category.CategoryRequest;
import com.sapo.store_management.dto.category.CategoryResponse;

import com.sapo.store_management.mapper.CategoryMapper;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.repository.CategoryRepo;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceIpml implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceIpml(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Page<CategoryResponse> getAllCategory(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Category> categories = categoryRepo.findAll(pageable);
        return categories.map(CategoryMapper::convertEntity);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find category"));
        CategoryResponse response = CategoryMapper.convertEntity(category);
        return response;
    }

    @Override
    public CategoryResponse insertCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.converCategory(categoryRequest);
        category = categoryRepo.save(category);
        CategoryResponse categoryResponse = CategoryMapper.convertEntity(category);
        return categoryResponse;
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No such category"));
        category.setName(categoryRequest.getName());
        category.setUpdated_at(LocalDateTime.now());
        category = categoryRepo.save(category);
        CategoryResponse categoryResponse = CategoryMapper.convertEntity(category);
        return categoryResponse;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No such category"));
        categoryRepo.delete(category);
    }
}
