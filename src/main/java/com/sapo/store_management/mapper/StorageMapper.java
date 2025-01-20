package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.storage.StorageCreateRequest;
import com.sapo.store_management.dto.storage.StorageRequest;
import com.sapo.store_management.dto.storage.StorageResponse;
import com.sapo.store_management.model.Storage;
import java.math.BigDecimal;

public class StorageMapper {

    /**
     * Convert CreateRequest to Storage entity
     */
    public static Storage toEntity(StorageCreateRequest createRequest) {
        return Storage.builder()
                .code(createRequest.getCode())
                .name(createRequest.getName())
                .address(createRequest.getAddress())
                .cash(createRequest.getCash())
                .balance(createRequest.getBalance())
                .build();
    }

    /**
     * Convert Request to Storage entity for update
     */
    public static Storage toEntity(StorageRequest request) {
        return Storage.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .address(request.getAddress())
                .cash(request.getCash())
                .balance(request.getBalance())
                .build();
    }

    /**
     * Convert Storage entity to Response DTO
     */
    public static StorageResponse toResponse(Storage storage) {
        return StorageResponse.builder()
                .id(storage.getId())
                .code(storage.getCode())
                .name(storage.getName())
                .address(storage.getAddress())
                .cash(BigDecimal.valueOf(storage.getCash()))
                .balance(BigDecimal.valueOf(storage.getBalance()))
                .createdAt(storage.getCreatedAt())
                .updatedAt(storage.getUpdatedAt())
                .build();
    }
}
