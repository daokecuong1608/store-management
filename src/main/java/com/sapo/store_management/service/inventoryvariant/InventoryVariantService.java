package com.sapo.store_management.service.inventoryvariant;

import com.sapo.store_management.dto.inventoryvariant.InventoryVariantCreateRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantResponse;
import com.sapo.store_management.model.InventoryVariant;

import java.util.List;

public interface InventoryVariantService {

    // Tạo InventoryVariant mới
    InventoryVariantResponse createInventoryVariant(InventoryVariantCreateRequest createRequest);

    // Cập nhật InventoryVariant
    InventoryVariantResponse updateInventoryVariant(InventoryVariantRequest request);

    // Xóa InventoryVariant
    void deleteInventoryVariant(Integer id);

    // Lấy tất cả InventoryVariant
    List<InventoryVariantResponse> getAllInventoryVariants();

    // Lấy InventoryVariant theo ID
    InventoryVariantResponse getInventoryVariantById(Integer id);

    // Lấy InventoryVariant Entity (dùng trong các Service khác)
    InventoryVariant getInventoryVariantEntityById(Integer id);
}