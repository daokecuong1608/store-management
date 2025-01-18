package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.inventory.InventoryCreateRequest;
import com.sapo.store_management.dto.inventory.InventoryRequest;
import com.sapo.store_management.dto.inventory.InventoryResponse;
import com.sapo.store_management.model.Inventory;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Storage;
import com.sapo.store_management.model.Supplier;

public class InventoryMapper {

    // Từ CreateRequest sang Entity
    public static Inventory toEntity(InventoryCreateRequest createRequest,Storage storage, Product product, Supplier supplier) {
        return Inventory.builder()
                .storage(storage) // Map Storage entity
                .product(product) // Map Product entity
                .supplier(supplier) // Map Supplier entity
                .build();
    }

    // Từ Request sang Entity (cho cập nhật)
    public static Inventory toEntity(InventoryRequest request, Storage storage, Product product, Supplier supplier) {
        return Inventory.builder()
                .id(request.getId())
                .storage(storage)
                .product(product)
                .supplier(supplier)
                .build();
    }


    // Từ Entity sang Response
    public static InventoryResponse toResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .storageId(inventory.getStorage().getId())
                .productId(inventory.getProduct().getId())
                .supplierId(inventory.getSupplier().getId())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}
