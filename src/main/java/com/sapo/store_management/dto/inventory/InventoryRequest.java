package com.sapo.store_management.dto.inventory;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InventoryRequest {
    @NotNull(message = "Inventory ID is required.")
    private Integer id;

    @NotNull(message = "Storage ID is required.")
    private Integer storageId;

    @NotNull(message = "Product ID is required.")
    private Integer productId;

    @NotNull(message = "Supplier ID is required.")
    private Integer supplierId;
}