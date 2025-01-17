package com.sapo.store_management.controller;


import com.sapo.store_management.dto.image.ImageRequest;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.service.img.ImageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) {

        try {
            Image savedImage = imageService.uploadAndSaveImage(file, uploadedBy);
            return ResponseEntity.ok(savedImage);
        } catch (Exception e) {
            System.out.println("ImageController: Error occurred - " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
