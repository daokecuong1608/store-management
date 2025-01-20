package com.sapo.store_management.dto.receiveinventory.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiveVariantResponse {
    private Integer id;
    private String sku;
    private String name;
    private Integer quantity;
} 