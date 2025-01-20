package com.sapo.store_management.controller;

import com.sapo.store_management.dto.inventory.InventoryCreateRequest;
import com.sapo.store_management.dto.inventory.InventoryRequest;
import com.sapo.store_management.dto.inventory.InventoryResponse;
import com.sapo.store_management.service.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

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

    @GetMapping("/search")
    public ResponseEntity<Page<InventoryResponse>> searchInventories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> branch,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Integer minStock,
            @RequestParam(required = false) Integer maxStock,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(inventoryService.searchInventories(
                page,
                pageSize,
                search,
                branch,
                startDate,
                endDate,
                minStock,
                maxStock,
                status
        ));
    }
}
