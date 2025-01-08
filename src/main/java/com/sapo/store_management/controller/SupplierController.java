package com.sapo.store_management.controller;

import com.sapo.store_management.dto.supplier.SupplierCreateRequest;
import com.sapo.store_management.dto.supplier.SupplierRequest;
import com.sapo.store_management.dto.supplier.SupplierResponse;
import com.sapo.store_management.service.supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Tạo mới Supplier
    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierCreateRequest createRequest) {
        SupplierResponse response = supplierService.createSupplier(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật Supplier
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable Integer id,
            @RequestBody SupplierRequest request) {
        // Đảm bảo ID từ URL và Request khớp nhau
        if (!id.equals(request.getId())) {
            return ResponseEntity.badRequest().build();
        }

        SupplierResponse response = supplierService.updateSupplier(request);
        return ResponseEntity.ok(response);
    }

    // Xóa Supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả các Supplier
    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        List<SupplierResponse> responses = supplierService.getAllSuppliers();
        return ResponseEntity.ok(responses);
    }

    // Lấy Supplier theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Integer id) {
        SupplierResponse response = supplierService.getSupplierById(id);
        return ResponseEntity.ok(response);
    }
}