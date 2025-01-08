package com.sapo.store_management.service.supplier;

import com.sapo.store_management.dto.supplier.SupplierCreateRequest;
import com.sapo.store_management.dto.supplier.SupplierRequest;
import com.sapo.store_management.dto.supplier.SupplierResponse;
import com.sapo.store_management.mapper.SupplierMapper;
import com.sapo.store_management.model.Supplier;
import com.sapo.store_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierResponse createSupplier(SupplierCreateRequest createRequest) {
        // Chuyển đổi từ DTO sang Entity
        Supplier supplier = SupplierMapper.toEntity(createRequest);

        // Lưu vào database
        supplier = supplierRepository.save(supplier);

        // Chuyển đổi sang Response DTO
        return SupplierMapper.toResponse(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(SupplierRequest request) {
        // Tìm kiếm Supplier hiện tại
        Supplier existingSupplier = supplierRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Chuyển đổi từ DTO sang Entity
        Supplier updatedSupplier = SupplierMapper.toEntity(request);

        // Preserve timestamps
        updatedSupplier.setCreatedAt(existingSupplier.getCreatedAt());

        // Lưu vào database
        updatedSupplier = supplierRepository.save(updatedSupplier);

        // Chuyển đổi sang Response DTO
        return SupplierMapper.toResponse(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Integer id) {
        // Tìm kiếm Supplier hiện tại
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Xóa Supplier
        supplierRepository.delete(supplier);
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        // Lấy danh sách tất cả Supplier
        List<Supplier> suppliers = supplierRepository.findAll();

        // Chuyển đổi sang danh sách Response DTO
        return suppliers.stream()
                .map(SupplierMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponse getSupplierById(Integer id) {
        // Tìm kiếm Supplier theo ID
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Chuyển đổi sang Response DTO
        return SupplierMapper.toResponse(supplier);
    }

    @Override
    public Supplier getSupplierEntityById(Integer id) {
        // Tìm kiếm Supplier Entity (cho các Service khác sử dụng)
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }
}