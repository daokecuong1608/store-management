package com.sapo.store_management.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierInfo {
    private Integer id;
    private String name;
    private String code;
    private String phone;
    private String email;
} 