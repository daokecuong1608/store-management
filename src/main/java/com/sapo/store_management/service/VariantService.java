package com.sapo.store_management.service;

import com.sapo.store_management.model.Variant;
import com.sapo.store_management.repository.VariantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantService {
    private final VariantRepo variantRepo;
    public VariantService(VariantRepo variantRepo) {
        this.variantRepo = variantRepo;
    }

    public List<Variant> getAllVariants() {return variantRepo.findAll();}

    public Variant getVariantById(int id) {return variantRepo.findById(id).get();}

    public void insertVariant(Variant variant) {variantRepo.save(variant);}

    public void deleteVariant(Variant variant) {variantRepo.delete(variant);}
}
