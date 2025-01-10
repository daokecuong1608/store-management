package com.sapo.store_management.mapper;


import com.sapo.store_management.dto.category.CategoryRequest;
import com.sapo.store_management.dto.category.CategoryResponse;
import com.sapo.store_management.model.Category;


public class CategoryMapper {
    public static Category converCategory(CategoryRequest categoryRequest){
        return  Category.builder()
                .name(categoryRequest.getName())
                .build();
    }


    public  static CategoryResponse convertEntity(Category category){
        return  CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .created_at(category.getCreated_at())
                .updated_at(category.getUpdated_at())
                .build();
    }

}
