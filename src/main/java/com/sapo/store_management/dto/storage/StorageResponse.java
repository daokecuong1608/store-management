package com.sapo.store_management.dto.storage;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StorageResponse {
    private Integer id;
    private String code;
    private String name;
    private String address;
    private Long cash;
    private Long balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
