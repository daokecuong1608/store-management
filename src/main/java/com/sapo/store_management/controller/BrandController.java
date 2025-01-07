package com.sapo.store_management.controller;

import com.sapo.store_management.model.Brand;
import com.sapo.store_management.service.BrandService;
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
    public List<Brand> getAllBrands() {return brandService.getAllBrands();}

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable int id) {return brandService.getBrandById(id);}

    @PostMapping("/insert")
    public void insertBrand(@RequestBody Brand brand) {brandService.insertBrand(brand);}

    @DeleteMapping("/delete")
    public void deleteBrand(@RequestBody Brand brand) {brandService.deleteBrand(brand);}
}
