package com.sapo.store_management.service;

import com.sapo.store_management.dto.VariantRequest;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.repository.VariantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VariantService {
    private static final Logger log = LoggerFactory.getLogger(VariantService.class);
    private final VariantRepo variantRepo;
    public VariantService(VariantRepo variantRepo) {
        this.variantRepo = variantRepo;
    }

    public List<Variant> getAllVariants() {return variantRepo.findAll();}

    public Variant getVariantById(int id) {return variantRepo.findById(id).get();}

    public void insertVariant(Variant variant) {variantRepo.save(variant);}

    public void deleteVariant(Variant variant) {variantRepo.delete(variant);}

    public VariantRequest updateVariant(int id, VariantRequest variantRequest) {
        Variant variant = variantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        log.info("Updating variant " + variantRequest);

        variant.setOption1(variantRequest.getOption1());
        variant.setOption2(variantRequest.getOption2());
        variant.setOption3(variantRequest.getOption3());
        variant.setImage_id(variantRequest.getImage_id());
        variant.setUpdated_at(new Date(System.currentTimeMillis()));
        variant.setPrice(variantRequest.getPrice());

        Variant update = variantRepo.save(variant);

        return new VariantRequest(
                update.getId(),
                update.getImage_id(),
                update.getOption1(),
                update.getOption2(),
                update.getOption3(),
                update.getPrice());
    }

    public Variant getVariantEntityById(Integer id) {
        return variantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
    }
}
