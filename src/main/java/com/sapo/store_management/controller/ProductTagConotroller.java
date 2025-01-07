package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductTagRequest;
import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.service.ProductTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producttag")
public class ProductTagConotroller {
    private final ProductTagService productTagService;
    public ProductTagConotroller(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @GetMapping("")
    public List<ProductTag> getProductTags() {return productTagService.getAllProductTags();}

    @GetMapping("/{id}")
    public ProductTag getProductTag(@PathVariable int id) {return productTagService.getProductTagById(id);}

    @PostMapping("/insert")
    public void insertProductTag(@RequestBody ProductTag productTag) {productTagService.insertProductTag(productTag);}

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductTagRequest> updateProductTag(@PathVariable int id, @RequestBody ProductTagRequest productTagRequest) {
        ProductTagRequest request = productTagService.updateProductTag(id, productTagRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteProductTag(@RequestBody ProductTag productTag) {productTagService.deleteProductTag(productTag);}

}
