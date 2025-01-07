package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ImageRequest;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Image>> getImages(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        Page<Image> images = imageService.getAllImages(page, size, sortBy);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@Valid @PathVariable int id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok(image);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Image> insertImage(@Valid @RequestBody Image image) {
        Image insertImage = imageService.insertImage(image);
        if (insertImage != null) {
            return ResponseEntity.ok(insertImage);
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ImageRequest> updateImage(@PathVariable int id, @RequestBody ImageRequest imageRequest) {
        ImageRequest request = imageService.updateImage(id, imageRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable int id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
