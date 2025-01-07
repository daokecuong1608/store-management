package com.sapo.store_management.controller;

import com.sapo.store_management.model.Variant;
import com.sapo.store_management.service.VariantService;
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

    @DeleteMapping("/delete")
    public void deleteVariant(@RequestBody Variant variant) {variantService.deleteVariant(variant);}
}
