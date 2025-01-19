package com.sapo.store_management.controller;

import com.sapo.store_management.service.img.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @PostMapping("/upload")
//    public ResponseEntity<Image> uploadImage(
//            @RequestPart("file") MultipartFile file) {
//
//        try {
//            Image savedImage = imageService.uploadAndSaveImage_2(file);
//            return ResponseEntity.ok(savedImage);
//        } catch (Exception e) {
//            System.out.println("ImageController: Error occurred - " + e.getMessage());
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

}
