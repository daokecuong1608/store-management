package com.sapo.store_management.controller;

import com.sapo.store_management.dto.VariantRequest;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.service.VariantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/variant")
public class VariantController {
    private final VariantService variantService;
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping("")
    public List<Variant> getAllVariants() {return variantService.getAllVariants();}

    @GetMapping("/{id}")
    public Variant getVariantById(@PathVariable int id) {return variantService.getVariantById(id);}

    @PostMapping("/insert")
    public void insertVariant(@RequestBody Variant variant) {variantService.insertVariant(variant);}

    @PutMapping("/update/{id}")
    public ResponseEntity<VariantRequest> updateVariant(@Valid @PathVariable int id, @RequestBody VariantRequest variantRequest) {
        VariantRequest request = variantService.updateVariant(id, variantRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteVariant(@RequestBody Variant variant) {variantService.deleteVariant(variant);}
}
