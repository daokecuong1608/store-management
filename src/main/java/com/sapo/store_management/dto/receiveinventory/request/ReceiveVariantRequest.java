package com.sapo.store_management.dto.receiveinventory.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReceiveVariantRequest {
    @NotNull(message = "Variant ID is required")
    private Integer variantId;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
} 