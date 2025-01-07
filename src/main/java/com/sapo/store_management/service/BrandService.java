package com.sapo.store_management.service;

import com.sapo.store_management.dto.BrandRequest;
import com.sapo.store_management.model.Brand;
import com.sapo.store_management.repository.BrandRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandService {
    private final BrandRepo brandRepo;

    public BrandService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public ResponseEntity<Page<Brand>> getAllBrands(int page, int size, String sortBy) {
        // Tạo Pageable với phân trang và sắp xếp
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        // Lấy dữ liệu từ Repository
        Page<Brand> brands = brandRepo.findAll(pageable);

        return ResponseEntity.ok(brands);
    }

    public Brand getBrandById(int id) {
        Brand brand = brandRepo.findById(id).orElseThrow(() -> new IllegalStateException("Could not find"));
        return brand;
    }

    public Brand insertBrand(Brand brand) {
        return brandRepo.save(brand);
    }

    public void deleteBrand(int id) {
        Brand brand = brandRepo.findById(id).orElseThrow(() -> new IllegalStateException("Could not find"));
        brandRepo.delete(brand);

    }

    public BrandRequest updateBrand(int id, BrandRequest brandRequest) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(brandRequest.getName());
        brand.setUpdated_at(new Date(System.currentTimeMillis()));
        Brand update = brandRepo.save(brand);
        return new BrandRequest(
                update.getId(),
                update.getName()
        );
    }
}
