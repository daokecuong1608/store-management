package com.sapo.store_management.controller;

import com.sapo.store_management.dto.option.OptionRequest;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Variant;
import com.sapo.store_management.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getlAllProducts(@RequestParam int page,
                                                                 @RequestParam int size,
                                                                 @RequestParam(defaultValue = "id") String sortBy) {
        Page<ProductResponse> product = productService.getAllProductResponse(page, size, sortBy);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse product = productService.getProductResponseByID(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> insertProduct(@Valid @RequestBody ProductRequest product) {
        ProductResponse insert = productService.createProductResponse(product);
        if (product != null) {
            return ResponseEntity.ok(insert);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest productDTO) {

        ProductResponse dto = productService.updateProductResponse(id, productDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductResponse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-tag")
    public ResponseEntity<List<ProductResponse>> getProductsByTagName(@RequestParam String tagName) {
        List<ProductResponse> products = productService.getProductsByTagName(tagName);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/generate-variants/{id}")
    public ResponseEntity<?> generateVariantsForProduct(@PathVariable Integer id, @Valid @RequestBody List<OptionRequest> inpuOptionRequests) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            // Tạo các variant từ inputOptions và cập nhật sản phẩm
            ProductResponse productResponse = productService.generateVariantsForProduct(product, inpuOptionRequests);
            return ResponseEntity.ok(productResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping("/by-name")
    public ResponseEntity<Page<ProductResponse>> getProductByName(@RequestParam String productName, @RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "name") String sortBy) {
        Page<ProductResponse> product = productService.getProductByName(productName, page, size, sortBy);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
