package com.sapo.store_management.controller;

import com.sapo.store_management.dto.brand.BrandRequest;
import com.sapo.store_management.dto.brand.BrandResponse;
import com.sapo.store_management.service.brand.BranhService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    private final BranhService brandService;

    public BrandController(BranhService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<Page<BrandResponse>> getAllBrands(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<BrandResponse> brands = brandService.getAllBrand(page, size, sortBy);
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@Valid @PathVariable Integer id) {
        BrandResponse brandResponse = brandService.getBrandById(id);
        if (brandResponse != null) {
            return ResponseEntity.ok(brandResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BrandResponse> insertBrand(@Valid @RequestBody BrandRequest brandRequest) {

        BrandResponse brandinsert = brandService.insertBrand(brandRequest);
        if (brandinsert != null) {
            return ResponseEntity.ok(brandinsert);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandRequest brandRequest) {
        BrandResponse brandResponse = brandService.updateBrand(id, brandRequest);
        if (brandResponse != null) {
            return ResponseEntity.ok(brandResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        // Trả về phản hồi thành công (mã HTTP 204)
        return ResponseEntity.noContent().build();
    }
}
