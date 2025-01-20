package com.sapo.store_management.dto.receiveinventory.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ReceiveProductResponse {
    private Integer id;
    private String code;
    private String name;
    private Integer quantity;
    private Long price;
    private List<ReceiveVariantResponse> variants;
} 