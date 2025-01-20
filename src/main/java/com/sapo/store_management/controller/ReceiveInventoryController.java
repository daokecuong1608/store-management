package com.sapo.store_management.controller;

import com.sapo.store_management.dto.common.DateRange;
import com.sapo.store_management.dto.common.NumberRange;
import com.sapo.store_management.dto.receiveinventory.request.DeleteRequest;
import com.sapo.store_management.dto.receiveinventory.request.ImportRequest;
import com.sapo.store_management.dto.receiveinventory.request.ReceiveInventoryRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveInventoryResponse;
import com.sapo.store_management.service.receiveinventory.ReceiveInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receive-inventories")
@RequiredArgsConstructor
public class ReceiveInventoryController {
    private final ReceiveInventoryService receiveInventoryService;

    @GetMapping
    public ResponseEntity<Page<ReceiveInventoryResponse>> getReceiveInventories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> branch,
            @RequestParam(required = false) DateRange dateRange,
            @RequestParam(required = false) NumberRange range,
            @RequestParam(required = false) List<String> status
    ) {
        Sort sort = null;
        if (sortField != null) {
            sort = Sort.by(sortOrder != null && sortOrder.equals("descend") ? 
                    Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        }
        
        return ResponseEntity.ok(receiveInventoryService.getReceiveInventories(
                PageRequest.of(page, pageSize, sort != null ? sort : Sort.unsorted()),
                search,
                branch,
                dateRange,
                range,
                status
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiveInventoryResponse> getReceiveInventory(@PathVariable Integer id) {
        return ResponseEntity.ok(receiveInventoryService.getReceiveInventory(id));
    }

    @PostMapping
    public ResponseEntity<ReceiveInventoryResponse> createReceiveInventory(
            @RequestBody ReceiveInventoryRequest request
    ) {
        return ResponseEntity.ok(receiveInventoryService.createReceiveInventory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiveInventoryResponse> updateReceiveInventory(
            @PathVariable Integer id,
            @RequestBody ReceiveInventoryRequest request
    ) {
        return ResponseEntity.ok(receiveInventoryService.updateReceiveInventory(id, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReceiveInventories(@RequestBody DeleteRequest request) {
        receiveInventoryService.deleteReceiveInventories(request.getIds());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/import")
    public ResponseEntity<Void> importInventory(
            @PathVariable Integer id,
            @RequestBody ImportRequest request
    ) {
        receiveInventoryService.importInventory(id, request.getProducts());
        return ResponseEntity.ok().build();
    }
} 