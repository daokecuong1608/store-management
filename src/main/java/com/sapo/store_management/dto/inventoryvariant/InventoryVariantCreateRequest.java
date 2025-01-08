package com.sapo.store_management.dto.inventoryvariant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InventoryVariantCreateRequest {
    @NotNull(message = "Inventory ID is required.")
    private Integer inventoryId;

    @NotNull(message = "Variant ID is required.")
    private Integer variantId;

    @PositiveOrZero(message = "Sold quantity must be zero or positive.")
    private Integer soldQuantity;

    @PositiveOrZero(message = "Stock quantity must be greater than or equal to zero.")
    private Integer stockQuantity;

    private String location; // Optional: Location of the inventory variant
}