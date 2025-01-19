package com.sapo.store_management.service;

import com.sapo.store_management.model.Variant;
import org.springframework.stereotype.Service;

@Service
public class VariantService {

    public Variant getVariantEntityById(Integer id){
        Variant variant = new Variant();
        return  variant;

    }

}
