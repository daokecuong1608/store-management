package com.sapo.store_management.controller;

import com.sapo.store_management.dto.BrandRequest;
import com.sapo.store_management.model.Brand;
import com.sapo.store_management.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Brand>> getAllBrands(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return brandService.getAllBrands(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@Valid @PathVariable int id) {
        Brand brand = brandService.getBrandById(id);
        if (brand != null) {
            return ResponseEntity.ok(brand);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Brand> insertBrand(@Valid @RequestBody Brand brand) {

        Brand brandinsert = brandService.insertBrand(brand);
        if (brandinsert != null) {
            return ResponseEntity.ok(brandinsert);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BrandRequest> updateBrand(@PathVariable int id, @RequestBody BrandRequest brandRequest) {
        BrandRequest request = brandService.updateBrand(id, brandRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
        // Trả về phản hồi thành công (mã HTTP 204)
        return ResponseEntity.noContent().build();
    }
}
