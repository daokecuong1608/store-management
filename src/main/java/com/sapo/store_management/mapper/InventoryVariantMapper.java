package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.inventoryvariant.InventoryVariantCreateRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantResponse;
import com.sapo.store_management.model.Inventory;
import com.sapo.store_management.model.InventoryVariant;
import com.sapo.store_management.model.Variant;

public class InventoryVariantMapper {
    /**
     * Convert CreateRequest to InventoryVariant entity
     */
    public static InventoryVariant toEntity(InventoryVariantCreateRequest createRequest, Inventory inventory, Variant variant) {
        return InventoryVariant.builder()
                .inventory(inventory)
                .variant(variant)
                .soldQuantity(createRequest.getSoldQuantity())
                .stockQuantity(createRequest.getStockQuantity())
                .location(createRequest.getLocation())
                .build();
    }

    /**
     * Convert Request to InventoryVariant entity for update
     */
    public static InventoryVariant toEntity(InventoryVariantRequest request, Inventory inventory, Variant variant) {
        return InventoryVariant.builder()
                .id(request.getId())
                .inventory(inventory)
                .variant(variant)
                .soldQuantity(request.getSoldQuantity())
                .stockQuantity(request.getStockQuantity())
                .location(request.getLocation())
                .build();
    }

    /**
     * Convert InventoryVariant entity to Response DTO
     */
    public static InventoryVariantResponse toResponse(InventoryVariant inventoryVariant) {
        return InventoryVariantResponse.builder()
                .id(inventoryVariant.getId())
                .inventoryId(inventoryVariant.getInventory().getId())
                .variantId(inventoryVariant.getVariant().getId())
                .soldQuantity(inventoryVariant.getSoldQuantity())
                .stockQuantity(inventoryVariant.getStockQuantity())
                .location(inventoryVariant.getLocation())
                .createdAt(inventoryVariant.getCreatedAt())
                .updatedAt(inventoryVariant.getUpdatedAt())
                .build();
    }
}
