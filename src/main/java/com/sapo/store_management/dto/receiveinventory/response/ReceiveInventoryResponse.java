package com.sapo.store_management.dto.receiveinventory.response;

import com.sapo.store_management.dto.common.SupplierInfo;
import com.sapo.store_management.dto.common.UserInfo;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiveInventoryResponse {
    private Integer id;
    private String code;
    private String status;
    private String note;
    private Double totalMoney;
    private Double totalQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String branch;
    private String staff;
    private LocalDateTime expectedDate;
    private String reference;
    private List<String> tags;
    private SupplierInfo supplier;
    private UserInfo createdBy;
    private UserInfo updatedBy;
    private List<ReceiveProductResponse> products;
} 