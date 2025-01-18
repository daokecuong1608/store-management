package com.sapo.store_management.service.supplier;

import com.sapo.store_management.dto.supplier.SupplierCreateRequest;
import com.sapo.store_management.dto.supplier.SupplierRequest;
import com.sapo.store_management.dto.supplier.SupplierResponse;
import com.sapo.store_management.model.Supplier;

import java.util.List;

public interface SupplierService {

    // Tạo Supplier mới
    SupplierResponse createSupplier(SupplierCreateRequest createRequest);

    // Cập nhật Supplier
    SupplierResponse updateSupplier(SupplierRequest request);

    // Xóa Supplier
    void deleteSupplier(Integer id);

    // Lấy tất cả Supplier
    List<SupplierResponse> getAllSuppliers();

    // Lấy Supplier theo ID
    SupplierResponse getSupplierById(Integer id);

    // Lấy Supplier entity (dùng trong các Service khác)
    Supplier getSupplierEntityById(Integer id);
}
