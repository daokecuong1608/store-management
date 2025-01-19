package com.sapo.store_management.dto.category;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class CategoryResponse {
    private Integer id;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
