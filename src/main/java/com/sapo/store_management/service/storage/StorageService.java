package com.sapo.store_management.service.storage;

import com.sapo.store_management.dto.storage.StorageCreateRequest;
import com.sapo.store_management.dto.storage.StorageRequest;
import com.sapo.store_management.dto.storage.StorageResponse;
import com.sapo.store_management.model.Storage;

import java.util.List;

public interface StorageService {

    // Tạo Storage mới
    StorageResponse createStorage(StorageCreateRequest createRequest);

    // Cập nhật Storage
    StorageResponse updateStorage(StorageRequest request);

    // Xóa Storage
    void deleteStorage(Integer id);

    // Lấy tất cả Storage
    List<StorageResponse> getAllStorages();

    // Lấy Storage theo ID
    StorageResponse getStorageById(Integer id);

    // Lấy Storage entity (dùng trong các Service khác)
    Storage getStorageEntityById(Integer id);

    Storage getStorage(Integer id);

    void updateProductQuantity(Integer storageId, String productId, Integer quantity);
}