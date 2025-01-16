package com.sapo.store_management.service.category;

import com.sapo.store_management.dto.category.CategoryRequest;
import com.sapo.store_management.dto.category.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    Page<CategoryResponse> getAllCategory(int page , int size , String sortBy);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Integer id);

    CategoryResponse insertCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory( Integer id, CategoryRequest categoryRequest);

    void deleteCategory(Integer id);

}
