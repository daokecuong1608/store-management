package com.sapo.store_management.service.storage;

import com.sapo.store_management.dto.storage.StorageResponse;
import com.sapo.store_management.repository.StorageRepository;
import com.sapo.store_management.dto.storage.StorageCreateRequest;
import com.sapo.store_management.dto.storage.StorageRequest;
import com.sapo.store_management.mapper.StorageMapper;
import com.sapo.store_management.model.Storage;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public StorageResponse createStorage(StorageCreateRequest createRequest) {
        // Chuyển đổi từ DTO sang Entity
        Storage storage = StorageMapper.toEntity(createRequest);

        // Lưu vào database
        storage = storageRepository.save(storage);

        // Chuyển đổi sang Response DTO
        return StorageMapper.toResponse(storage);
    }

    @Override
    public StorageResponse updateStorage(StorageRequest request) {
        // Tìm kiếm Storage hiện tại
        Storage existingStorage = storageRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Storage not found"));

        // Chuyển đổi từ DTO sang Entity
        Storage updatedStorage = StorageMapper.toEntity(request);

        // Preserve timestamps
        updatedStorage.setCreatedAt(existingStorage.getCreatedAt());

        // Lưu vào database
        updatedStorage = storageRepository.save(updatedStorage);

        // Chuyển đổi sang Response DTO
        return StorageMapper.toResponse(updatedStorage);
    }

    @Override
    public void deleteStorage(Integer id) {
        // Tìm kiếm Storage hiện tại
        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found"));

        // Xóa Storage
        storageRepository.delete(storage);
    }

    @Override
    public List<StorageResponse> getAllStorages() {
        // Lấy danh sách tất cả Storage
        List<Storage> storages = storageRepository.findAll();

        // Chuyển đổi sang danh sách Response DTO
        return storages.stream()
                .map(StorageMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StorageResponse getStorageById(Integer id) {
        // Tìm kiếm Storage theo ID
        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found"));

        // Chuyển đổi sang Response DTO
        return StorageMapper.toResponse(storage);
    }

    @Override
    public Storage getStorageEntityById(Integer id) {
        // Tìm kiếm Storage Entity (cho các Service khác sử dụng)
        return storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found"));
    }
}