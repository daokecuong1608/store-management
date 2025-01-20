package com.sapo.store_management.service.receiveinventory;

import com.sapo.store_management.dto.common.DateRange;
import com.sapo.store_management.dto.common.NumberRange;
import com.sapo.store_management.dto.receiveinventory.request.ReceiveInventoryRequest;
import com.sapo.store_management.dto.receiveinventory.request.ProductImportRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveInventoryResponse;
import com.sapo.store_management.exception.ResourceNotFoundException;
import com.sapo.store_management.mapper.ReceiveInventoryMapper;
import com.sapo.store_management.model.ReceiveInventory;
import com.sapo.store_management.repository.ReceiveInventoryRepository;
import com.sapo.store_management.service.product.ProductService;
import com.sapo.store_management.service.storage.StorageService;
import com.sapo.store_management.service.supplier.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiveInventoryServiceImpl implements ReceiveInventoryService {
    private final ReceiveInventoryRepository receiveInventoryRepository;
    private final ReceiveInventoryMapper receiveInventoryMapper;
    private final ProductService productService;
    private final StorageService storageService;
    private final SupplierService supplierService;

    @Override
    public Page<ReceiveInventoryResponse> getReceiveInventories(
            Pageable pageable,
            String search,
            List<String> branch,
            DateRange dateRange,
            NumberRange range,
            List<String> status
    ) {
        Specification<ReceiveInventory> spec = Specification.where(null);
        
        if (search != null) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("code")), "%" + search.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("note")), "%" + search.toLowerCase() + "%")
                )
            );
        }
        
        if (branch != null && !branch.isEmpty()) {
            spec = spec.and((root, query, cb) -> root.get("storage").get("code").in(branch));
        }
        
        if (dateRange != null) {
            spec = spec.and((root, query, cb) -> 
                cb.between(root.get("createdAt"), dateRange.getStart(), dateRange.getEnd())
            );
        }
        
        if (status != null && !status.isEmpty()) {
            spec = spec.and((root, query, cb) -> root.get("status").in(status));
        }

        return receiveInventoryRepository.findAll(spec, pageable)
                .map(receiveInventoryMapper::toResponse);
    }

    @Override
    public ReceiveInventoryResponse getReceiveInventory(Integer id) {
        return receiveInventoryRepository.findById(id)
                .map(receiveInventoryMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Receive inventory not found"));
    }

    @Override
    @Transactional
    public ReceiveInventoryResponse createReceiveInventory(ReceiveInventoryRequest request) {
        // Validate references
        supplierService.getSupplierEntityById(request.getSupplierId());
        storageService.getStorageEntityById(request.getStorageId());
        request.getProducts().forEach(product -> 
            productService.getProductEntityById(product.getProductId())
        );

        ReceiveInventory entity = receiveInventoryMapper.toEntity(request);
        entity.setStatus("PENDING");
        
        return receiveInventoryMapper.toResponse(receiveInventoryRepository.save(entity));
    }

    @Override
    @Transactional
    public ReceiveInventoryResponse updateReceiveInventory(Integer id, ReceiveInventoryRequest request) {
        ReceiveInventory existing = receiveInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receive inventory not found"));
                
        if ("COMPLETED".equals(existing.getStatus())) {
            throw new IllegalStateException("Cannot update completed receive inventory");
        }

        // Validate references
        supplierService.getSupplier(request.getSupplierId());
        storageService.getStorage(request.getStorageId());
        request.getProducts().forEach(product -> 
            productService.getProductEntityById(product.getProductId())
        );

        ReceiveInventory updated = receiveInventoryMapper.toEntity(request);
        updated.setId(id);
        updated.setStatus(existing.getStatus());
        updated.setCreatedAt(existing.getCreatedAt());
        
        return receiveInventoryMapper.toResponse(receiveInventoryRepository.save(updated));
    }

    @Override
    @Transactional
    public void deleteReceiveInventories(List<Integer> ids) {
        List<ReceiveInventory> inventories = receiveInventoryRepository.findAllById(ids);
        
        inventories.forEach(inventory -> {
            if ("COMPLETED".equals(inventory.getStatus())) {
                throw new IllegalStateException("Cannot delete completed receive inventory");
            }
        });
        
        receiveInventoryRepository.deleteAllById(ids);
    }

    @Override
    @Transactional
    public void importInventory(Integer id, List<ProductImportRequest> products) {
        ReceiveInventory inventory = receiveInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receive inventory not found"));
                
        if ("COMPLETED".equals(inventory.getStatus())) {
            throw new IllegalStateException("Receive inventory already completed");
        }

        // Update inventory status
        inventory.setStatus("COMPLETED");
        receiveInventoryRepository.save(inventory);

        // Update product quantities in storage
        products.forEach(product -> {
            storageService.updateProductQuantity(
                inventory.getStorage().getId(),
                product.getId(),
                product.getQuantity()
            );
        });
    }
} 