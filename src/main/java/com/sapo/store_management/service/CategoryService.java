package com.sapo.store_management.service;

import com.sapo.store_management.model.Category;
import com.sapo.store_management.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {return categoryRepo.findAll();}

    public Category findById(int id) {return categoryRepo.getReferenceById(id);}

    public void saveCategory(Category category) {categoryRepo.save(category);}

    public void deleteCategory(Category category) {categoryRepo.delete(category);}
}
