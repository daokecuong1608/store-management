package com.sapo.store_management.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantResponse {
    private String variantDescription; // Mô tả của biến thể (e.g., "S-Đỏ")
    private int price;
}