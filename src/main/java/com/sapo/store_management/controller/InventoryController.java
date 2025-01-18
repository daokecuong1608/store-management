package com.sapo.store_management.controller;

import com.sapo.store_management.dto.inventory.InventoryCreateRequest;
import com.sapo.store_management.dto.inventory.InventoryRequest;
import com.sapo.store_management.dto.inventory.InventoryResponse;
import com.sapo.store_management.service.inventory.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Tạo mới Inventory
    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryCreateRequest createRequest) {
        InventoryResponse response = inventoryService.createInventory(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật Inventory
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Integer id,
            @RequestBody InventoryRequest request) {
        // Đảm bảo ID từ URL và Request khớp nhau
        if (!id.equals(request.getId())) {
            return ResponseEntity.badRequest().build();
        }

        InventoryResponse response = inventoryService.updateInventory(request);
        return ResponseEntity.ok(response);
    }

    // Xóa Inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả Inventory
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventories() {
        List<InventoryResponse> responses = inventoryService.getAllInventories();
        return ResponseEntity.ok(responses);
    }

    // Lấy Inventory theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Integer id) {
        InventoryResponse response = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(response);
    }
}
