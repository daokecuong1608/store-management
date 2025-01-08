package com.sapo.store_management.controller;

import com.sapo.store_management.dto.inventoryvariant.InventoryVariantCreateRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantResponse;
import com.sapo.store_management.service.inventoryvariant.InventoryVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-variants")
public class InventoryVariantController {

    private final InventoryVariantService inventoryVariantService;

    public InventoryVariantController(InventoryVariantService inventoryVariantService) {
        this.inventoryVariantService = inventoryVariantService;
    }

    // Tạo mới InventoryVariant
    @PostMapping
    public ResponseEntity<InventoryVariantResponse> createInventoryVariant(@RequestBody InventoryVariantCreateRequest createRequest) {
        InventoryVariantResponse response = inventoryVariantService.createInventoryVariant(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật InventoryVariant
    @PutMapping("/{id}")
    public ResponseEntity<InventoryVariantResponse> updateInventoryVariant(
            @PathVariable Integer id,
            @RequestBody InventoryVariantRequest request) {
        // Đảm bảo ID từ URL và Request khớp nhau
        if (!id.equals(request.getId())) {
            return ResponseEntity.badRequest().build();
        }

        InventoryVariantResponse response = inventoryVariantService.updateInventoryVariant(request);
        return ResponseEntity.ok(response);
    }

    // Xóa InventoryVariant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryVariant(@PathVariable Integer id) {
        inventoryVariantService.deleteInventoryVariant(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả InventoryVariant
    @GetMapping
    public ResponseEntity<List<InventoryVariantResponse>> getAllInventoryVariants() {
        List<InventoryVariantResponse> responses = inventoryVariantService.getAllInventoryVariants();
        return ResponseEntity.ok(responses);
    }

    // Lấy InventoryVariant theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryVariantResponse> getInventoryVariantById(@PathVariable Integer id) {
        InventoryVariantResponse response = inventoryVariantService.getInventoryVariantById(id);
        return ResponseEntity.ok(response);
    }
}
