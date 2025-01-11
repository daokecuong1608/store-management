package com.sapo.store_management.service.inventory;

import com.sapo.store_management.dto.inventory.InventoryCreateRequest;
import com.sapo.store_management.dto.inventory.InventoryRequest;
import com.sapo.store_management.dto.inventory.InventoryResponse;
import com.sapo.store_management.mapper.InventoryMapper;
import com.sapo.store_management.model.Inventory;
import com.sapo.store_management.repository.InventoryRepository;
import com.sapo.store_management.service.product.ProductService;
import com.sapo.store_management.service.storage.StorageService;
import com.sapo.store_management.service.supplier.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StorageService storageService;
    private final ProductService productService;
    private final SupplierService supplierService;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                StorageService storageService,
                                ProductService productService,
                                SupplierService supplierService) {
        this.inventoryRepository = inventoryRepository;
        this.storageService = storageService;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @Override
    public InventoryResponse createInventory(InventoryCreateRequest createRequest) {
        // Tìm kiếm các entity qua Service
        var storage = storageService.getStorageEntityById(createRequest.getStorageId());
        var product = productService.getProductById(createRequest.getProductId());
        var supplier = supplierService.getSupplierEntityById(createRequest.getSupplierId());

        // Chuyển đổi DTO sang Entity
        Inventory inventory = InventoryMapper.toEntity(createRequest, storage, product, supplier);

        // Lưu vào database
        inventory = inventoryRepository.save(inventory);

        // Trả về Response DTO
        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse updateInventory(InventoryRequest request) {
        // Tìm kiếm Inventory hiện tại
        Inventory existingInventory = inventoryRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // Tìm kiếm các entity qua Service
        var storage = storageService.getStorageEntityById(request.getStorageId());
        var product = productService.getProductById(request.getProductId());
        var supplier = supplierService.getSupplierEntityById(request.getSupplierId());

        // Cập nhật Entity
        Inventory updatedInventory = InventoryMapper.toEntity(request, storage, product, supplier);

        // Preserve timestamps
        updatedInventory.setCreatedAt(existingInventory.getCreatedAt());

        // Lưu lại vào database
        updatedInventory = inventoryRepository.save(updatedInventory);

        // Trả về Response DTO
        return InventoryMapper.toResponse(updatedInventory);
    }

    @Override
    public void deleteInventory(Integer id) {
        // Tìm kiếm Inventory hiện tại
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // Xóa Inventory
        inventoryRepository.delete(inventory);
    }

    @Override
    public List<InventoryResponse> getAllInventories() {
        // Lấy danh sách tất cả Inventory
        List<Inventory> inventories = inventoryRepository.findAll();

        // Chuyển đổi sang danh sách Response DTO
        return inventories.stream()
                .map(InventoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse getInventoryById(Integer id) {
        // Tìm kiếm Inventory theo ID
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // Chuyển đổi sang Response DTO
        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public Inventory getInventoryEntityById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}