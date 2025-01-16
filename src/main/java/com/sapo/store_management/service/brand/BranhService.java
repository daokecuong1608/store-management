package com.sapo.store_management.service.brand;

import com.sapo.store_management.dto.brand.BrandRequest;
import com.sapo.store_management.dto.brand.BrandResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranhService {

    Page<BrandResponse> getAllBrand(int page , int size , String sortBy);

    List<BrandResponse> getAllBrands();

    BrandResponse getBrandById(Integer id);

    BrandResponse insertBrand(BrandRequest BrandRequest);

    BrandResponse updateBrand( Integer id, BrandRequest brandRequest);

    void deleteBrand(Integer id);

    Page<BrandResponse> findByNameBrand(String brandName , int page , int size , String sortBy);
}
