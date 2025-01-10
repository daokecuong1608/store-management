package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.brand.BrandRequest;
import com.sapo.store_management.dto.brand.BrandResponse;
import com.sapo.store_management.model.Brand;


public class BrandMapper {
    public static Brand converBrand(BrandRequest brandRequest){
        return  Brand.builder()
                .name(brandRequest.getName())
                .build();
    }



    public  static BrandResponse convertEntity(Brand brand){
        return  BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .created_at(brand.getCreated_at())
                .updated_at(brand.getUpdated_at())
                .build();
    }

}
