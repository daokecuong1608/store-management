package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ProductTagRequest;
import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.service.ProductTagService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<ProductTag>>getProductTags(@RequestParam int page,
                                                          @RequestParam int size,
                                                          @RequestParam(defaultValue = "id") String sortBy) {
        Page<ProductTag> productTags = productTagService.getAllProductTags(page, size, sortBy);
        return ResponseEntity.ok(productTags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTag> getProductTag(@PathVariable int id) {
        ProductTag tag = productTagService.getProductTagById(id);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<ProductTag> insertProductTag(@Valid @RequestBody ProductTag productTag) {
        ProductTag tag = productTagService.insertProductTag(productTag);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductTagRequest> updateProductTag(@PathVariable int id, @RequestBody ProductTagRequest productTagRequest) {
        ProductTagRequest request = productTagService.updateProductTag(id, productTagRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductTag(@PathVariable int id) {
        productTagService.deleteProductTag(id);
        return ResponseEntity.ok().build();
    }

}
