package com.sapo.store_management.controller;

import com.sapo.store_management.dto.VariantRequest;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.service.VariantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Variant>> getAllVariants(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Page<Variant> var = variantService.getAllVariants(page, size, sortBy);
        return ResponseEntity.ok(var);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variant> getVariantById(@PathVariable int id) {
        Variant var = variantService.getVariantById(id);
        if (var != null) {
            return ResponseEntity.ok(var);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Variant> insertVariant(@Valid @RequestBody Variant variant) {
        Variant var = variantService.insertVariant(variant);
        if (var == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(var);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VariantRequest> updateVariant(@Valid @PathVariable int id, @RequestBody VariantRequest variantRequest) {
        VariantRequest request = variantService.updateVariant(id, variantRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVariant(@PathVariable int id) {
        variantService.deleteVariant(id);
        return ResponseEntity.ok().build();
    }
}
