package com.sapo.store_management.dto.brand;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class BrandResponse {
    private Integer id;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
