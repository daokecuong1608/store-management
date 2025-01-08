package com.sapo.store_management.dto.inventoryvariant;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryVariantResponse {
    private Integer id;
    private Integer inventoryId;
    private Integer variantId;
    private Integer soldQuantity;
    private Integer stockQuantity;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}