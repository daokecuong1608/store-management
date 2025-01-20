package com.sapo.store_management.dto.receiveinventory.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class ReceiveProductRequest {
    @NotNull(message = "Product ID is required")
    private Integer productId;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Long price;
    private List<ReceiveVariantRequest> variants;
} 