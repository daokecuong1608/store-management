package com.sapo.store_management.dto.product;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
    private String brand_name;
    private String category_name;
    private String tag_name;
}
