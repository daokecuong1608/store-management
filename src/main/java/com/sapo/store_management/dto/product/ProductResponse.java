package com.sapo.store_management.dto.product;

import com.sapo.store_management.dto.brand.BrandResponse;
import com.sapo.store_management.dto.category.CategoryResponse;
import com.sapo.store_management.dto.option.OptionResponse;
import com.sapo.store_management.dto.tag.TagResponse;
import com.sapo.store_management.dto.variant.VariantResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private int price;
    private int capital_price;
    private String image;
    private boolean status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private BrandResponse brandResponse;
    private List<CategoryResponse> categoryResponses;
    private List<TagResponse> tags_name;
    private List<OptionResponse> options;
    private List<VariantResponse> variants;
}
