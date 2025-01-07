package com.sapo.store_management.service;

import com.sapo.store_management.model.Brand;
import com.sapo.store_management.repository.BrandRepo;
import org.springframework.stereotype.Service;

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
}
