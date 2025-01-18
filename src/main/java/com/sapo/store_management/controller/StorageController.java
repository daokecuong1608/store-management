package com.sapo.store_management.controller;

import com.sapo.store_management.dto.storage.StorageCreateRequest;
import com.sapo.store_management.dto.storage.StorageRequest;
import com.sapo.store_management.dto.storage.StorageResponse;
import com.sapo.store_management.service.storage.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storages")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    // Tạo mới Storage
    @PostMapping
    public ResponseEntity<StorageResponse> createStorage(@RequestBody StorageCreateRequest createRequest) {
        StorageResponse response = storageService.createStorage(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật Storage
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorage(@PathVariable Integer id) {
        storageService.deleteStorage(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả các Storage
    @GetMapping
    public ResponseEntity<List<StorageResponse>> getAllStorages() {
        List<StorageResponse> responses = storageService.getAllStorages();
        return ResponseEntity.ok(responses);
    }

    // Lấy Storage theo ID
    @GetMapping("/{id}")
    public ResponseEntity<StorageResponse> getStorageById(@PathVariable Integer id) {
        StorageResponse response = storageService.getStorageById(id);
        return ResponseEntity.ok(response);
    }
}