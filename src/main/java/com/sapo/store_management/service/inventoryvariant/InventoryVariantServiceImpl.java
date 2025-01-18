package com.sapo.store_management.service.inventoryvariant;

import com.sapo.store_management.dto.inventoryvariant.InventoryVariantCreateRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantRequest;
import com.sapo.store_management.dto.inventoryvariant.InventoryVariantResponse;
import com.sapo.store_management.mapper.InventoryVariantMapper;
import com.sapo.store_management.model.Inventory;
import com.sapo.store_management.model.InventoryVariant;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.repository.InventoryVariantRepository;
import com.sapo.store_management.service.VariantService;
import com.sapo.store_management.service.inventory.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryVariantServiceImpl implements InventoryVariantService {

    private final InventoryVariantRepository inventoryVariantRepository;
    private final InventoryService inventoryService;
    private final VariantService variantService;

    public InventoryVariantServiceImpl(InventoryVariantRepository inventoryVariantRepository,
                                       InventoryService inventoryService,
                                       VariantService variantService) {
        this.inventoryVariantRepository = inventoryVariantRepository;
        this.inventoryService = inventoryService;
        this.variantService = variantService;
    }

    @Override
    public InventoryVariantResponse createInventoryVariant(InventoryVariantCreateRequest createRequest) {
        // Sử dụng Service để lấy Inventory và Variant Entity
        Inventory inventory = inventoryService.getInventoryEntityById(createRequest.getInventoryId());
        Variant variant = variantService.getVariantEntityById(createRequest.getVariantId());

        // Chuyển đổi từ DTO sang Entity
        InventoryVariant inventoryVariant = InventoryVariantMapper.toEntity(createRequest, inventory, variant);

        // Lưu vào database
        inventoryVariant = inventoryVariantRepository.save(inventoryVariant);

        // Chuyển đổi sang Response DTO
        return InventoryVariantMapper.toResponse(inventoryVariant);
    }

    @Override
    public InventoryVariantResponse updateInventoryVariant(InventoryVariantRequest request) {
        // Tìm InventoryVariant hiện tại
        InventoryVariant existingVariant = inventoryVariantRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("InventoryVariant not found"));

        // Sử dụng Service để lấy Inventory và Variant Entity
        Inventory inventory = inventoryService.getInventoryEntityById(request.getInventoryId());
        Variant variant = variantService.getVariantEntityById(request.getVariantId());

        // Chuyển đổi từ DTO sang Entity
        InventoryVariant updatedVariant = InventoryVariantMapper.toEntity(request, inventory, variant);

        // Preserve timestamps
        updatedVariant.setCreatedAt(existingVariant.getCreatedAt());

        // Lưu lại vào database
        updatedVariant = inventoryVariantRepository.save(updatedVariant);

        // Chuyển đổi sang Response DTO
        return InventoryVariantMapper.toResponse(updatedVariant);
    }

    @Override
    public void deleteInventoryVariant(Integer id) {
        // Tìm InventoryVariant hiện tại
        InventoryVariant inventoryVariant = inventoryVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InventoryVariant not found"));

        // Xóa InventoryVariant
        inventoryVariantRepository.delete(inventoryVariant);
    }

    @Override
    public List<InventoryVariantResponse> getAllInventoryVariants() {
        // Lấy danh sách tất cả InventoryVariant
        List<InventoryVariant> variants = inventoryVariantRepository.findAll();

        // Chuyển đổi sang danh sách Response DTO
        return variants.stream()
                .map(InventoryVariantMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryVariantResponse getInventoryVariantById(Integer id) {
        // Tìm InventoryVariant theo ID
        InventoryVariant inventoryVariant = inventoryVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InventoryVariant not found"));

        // Chuyển đổi sang Response DTO
        return InventoryVariantMapper.toResponse(inventoryVariant);
    }

    @Override
    public InventoryVariant getInventoryVariantEntityById(Integer id) {
        // Tìm InventoryVariant Entity (cho các Service khác sử dụng)
        return inventoryVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InventoryVariant not found"));
    }
}