package com.sapo.store_management.dto.storage;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class StorageResponse {
    private Integer id;
    private String code;
    private String name;
    private String address;
    private BigDecimal cash;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
