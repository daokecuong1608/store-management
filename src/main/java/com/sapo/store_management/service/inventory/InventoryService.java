package com.sapo.store_management.service.inventory;

import com.sapo.store_management.dto.inventory.InventoryCreateRequest;
import com.sapo.store_management.dto.inventory.InventoryRequest;
import com.sapo.store_management.dto.inventory.InventoryResponse;
import com.sapo.store_management.model.Inventory;

import java.util.List;

public interface InventoryService {

    // Tạo Inventory mới
    InventoryResponse createInventory(InventoryCreateRequest createRequest);

    // Cập nhật Inventory
    InventoryResponse updateInventory(InventoryRequest request);

    // Xóa Inventory
    void deleteInventory(Integer id);

    // Lấy tất cả Inventory
    List<InventoryResponse> getAllInventories();

    // Lấy Inventory theo ID
    InventoryResponse getInventoryById(Integer id);

    // Lấy Inventory entity (dùng trong các Service khác)
    Inventory getInventoryEntityById(Integer id);
}
