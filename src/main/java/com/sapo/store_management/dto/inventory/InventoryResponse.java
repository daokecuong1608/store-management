package com.sapo.store_management.dto.inventory;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class InventoryResponse {
    private Integer id;
    private Integer storageId;
//    private String storageName;
    private Integer productId;
    private Integer supplierId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
