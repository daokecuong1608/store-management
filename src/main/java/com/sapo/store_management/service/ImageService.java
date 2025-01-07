package com.sapo.store_management.service;

import com.sapo.store_management.model.Image;
import com.sapo.store_management.repository.ImageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private final ImageRepo imageRepo;
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public List<Image> getAllImages() {return imageRepo.findAll();}

    public Image getImageById(int id) {return imageRepo.getReferenceById(id);}

    public void insertImage(Image image) {imageRepo.save(image);}

    public void updateImage(Image image) {imageRepo.save(image);}

    public void deleteImage(Image image) {imageRepo.delete(image);}
}
