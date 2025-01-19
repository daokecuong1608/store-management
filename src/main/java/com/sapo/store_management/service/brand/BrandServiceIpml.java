package com.sapo.store_management.service.brand;

import com.sapo.store_management.dto.brand.BrandRequest;
import com.sapo.store_management.dto.brand.BrandResponse;
import com.sapo.store_management.mapper.BrandMapper;
import com.sapo.store_management.model.Brand;
import com.sapo.store_management.repository.BrandRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceIpml implements BranhService {

    private final BrandRepo brandRepo;

    public BrandServiceIpml(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @Override
    public Page<BrandResponse> getAllBrand(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Brand> brand = brandRepo.findAll(pageable);
        return brand.map(BrandMapper::convertEntity);
    }

    @Override
    public List<BrandResponse> getAllBrands(){
        List<Brand> brands = brandRepo.findAll();
        List<BrandResponse> brandResponses = new ArrayList<>();
        for (Brand brand : brands) {
            brandResponses.add(BrandMapper.convertEntity(brand));
        }
        return brandResponses;
    }

    @Override
    public BrandResponse getBrandById(Integer id) {
        Brand baBrand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find"));

        return BrandMapper.convertEntity(baBrand);
    }

    @Override
    public BrandResponse insertBrand(BrandRequest branhCreateRequest) {
        Brand brand = BrandMapper.converBrand(branhCreateRequest);
        brand = brandRepo.save(brand);
        return BrandMapper.convertEntity(brand);
    }

    @Override
    public BrandResponse updateBrand(Integer id, BrandRequest brandRequest) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find brand with id: " + id));

        brand.setName(brandRequest.getName());
        brand.setUpdated_at(LocalDateTime.now());
        Brand updatedBrand = brandRepo.save(brand);
        return BrandMapper.convertEntity(updatedBrand);
    }


    @Override
    public void deleteBrand(Integer id) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find brand with id: " + id));
        brandRepo.delete(brand);
    }

    @Override
    public Page<BrandResponse> findByNameBrand(String brandName , int page , int size , String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        String formatBrandName = "%" + brandName + "%";
        Page<Brand> brand = brandRepo.findByNameBrand( formatBrandName, pageable);
        return brand.map(BrandMapper::convertEntity);
    }
}
