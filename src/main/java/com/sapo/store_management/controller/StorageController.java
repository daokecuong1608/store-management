package com.sapo.store_management.controller;

import com.sapo.store_management.dto.storage.StorageCreateRequest;
import com.sapo.store_management.dto.storage.StorageRequest;
import com.sapo.store_management.dto.storage.StorageResponse;
import com.sapo.store_management.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    // Tạo mới Storage
    @PostMapping("/warehouses")
    public ResponseEntity<StorageResponse> createStorage(@RequestBody StorageCreateRequest request) {
        return ResponseEntity.ok(storageService.createStorage(request));
    }

    // Cập nhật Storage
    @PutMapping("/warehouses/{id}")
    public ResponseEntity<StorageResponse> updateStorage(
            @PathVariable Integer id,
            @RequestBody StorageRequest request) {
        // Đảm bảo ID từ URL và Request khớp nhau
        if (!id.equals(request.getId())) {
            return ResponseEntity.badRequest().build();
        }

        StorageResponse response = storageService.updateStorage(request);
        return ResponseEntity.ok(response);
    }

    // Xóa Storage
    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<Void> deleteStorage(@PathVariable Integer id) {
        storageService.deleteStorage(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả các Storage
    @GetMapping("/warehouses")
    public ResponseEntity<List<StorageResponse>> getAllStorages() {
        return ResponseEntity.ok(storageService.getAllStorages());
    }

    // Lấy Storage theo ID
    @GetMapping("/warehouses/{id}")
    public ResponseEntity<StorageResponse> getStorageById(@PathVariable Integer id) {
        StorageResponse response = storageService.getStorageById(id);
        return ResponseEntity.ok(response);
    }
}