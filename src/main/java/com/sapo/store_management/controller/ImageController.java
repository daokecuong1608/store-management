package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ImageRequest;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.service.ImageService;
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
    public List<Image> getImages() {return imageService.getAllImages();}

    @GetMapping("/{id}")
    public Image getImage(@PathVariable int id) {return imageService.getImageById(id);}

    @PostMapping("/insert")
    public void insertImage(@RequestBody Image image) {imageService.insertImage(image);}

    @PutMapping("/update/{id}")
    public ResponseEntity<ImageRequest> updateImage(@PathVariable int id, @RequestBody ImageRequest imageRequest) {
        ImageRequest request = imageService.updateImage(id, imageRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteImage(@RequestBody Image image) {imageService.deleteImage(image);}
}
