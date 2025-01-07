package com.sapo.store_management.service;

import com.sapo.store_management.dto.BrandRequest;
import com.sapo.store_management.model.Brand;
import com.sapo.store_management.repository.BrandRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandService {
    private final BrandRepo brandRepo;
    public BrandService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public List<Brand> getAllBrands() {return brandRepo.findAll();}

    public Brand getBrandById(int id) {return brandRepo.findById(id).get();}

    public void insertBrand(Brand brand) {brandRepo.save(brand);}

    public void deleteBrand(Brand brand) {brandRepo.delete(brand);}

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
