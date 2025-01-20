package com.sapo.store_management.dto.receiveinventory.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiveInventoryRequest {
    private String code;
    @NotNull(message = "Supplier ID is required")
    private Integer supplierId;
    @NotNull(message = "Storage ID is required")
    private Integer storageId;
    @NotNull(message = "Staff ID is required")
    private Integer staffId;
    private LocalDateTime expectedDate;
    private String reference;
    private String note;
    private List<String> tags;
    private List<ReceiveProductRequest> products;
} 