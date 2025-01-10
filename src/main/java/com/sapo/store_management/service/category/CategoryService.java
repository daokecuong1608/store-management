package com.sapo.store_management.service.category;

import com.sapo.store_management.dto.brand.BrandRequest;
import com.sapo.store_management.dto.brand.BrandResponse;
import com.sapo.store_management.dto.category.CategoryRequest;
import com.sapo.store_management.dto.category.CategoryResponse;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<CategoryResponse> getAllCategory(int page , int size , String sortBy);

    CategoryResponse getCategoryById(Integer id);

    CategoryResponse insertCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory( Integer id, CategoryRequest categoryRequest);

    void deleteCategory(Integer id);

}
