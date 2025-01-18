package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.supplier.SupplierCreateRequest;
import com.sapo.store_management.dto.supplier.SupplierRequest;
import com.sapo.store_management.dto.supplier.SupplierResponse;
import com.sapo.store_management.model.Supplier;

public class SupplierMapper {

    /**
     * Convert CreateRequest to Supplier entity
     */
    public static Supplier toEntity(SupplierCreateRequest createRequest) {
        return Supplier.builder()
                .code(createRequest.getCode())
                .name(createRequest.getName())
                .address(createRequest.getAddress())
                .phone(createRequest.getPhone())
                .email(createRequest.getEmail())
                .build();
    }

    /**
     * Convert Request to Supplier entity for update
     */
    public static Supplier toEntity(SupplierRequest request) {
        return Supplier.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }

    /**
     * Convert Supplier entity to Response DTO
     */
    public static SupplierResponse toResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .code(supplier.getCode())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}

