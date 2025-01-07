package com.sapo.store_management.service;

import com.sapo.store_management.dto.ImageRequest;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.repository.ImageRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public ImageRequest updateImage(int id, ImageRequest imageRequest) {
        Image image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        image.setImage_url(imageRequest.getImage_url());
        image.setProduct_id(imageRequest.getProduct_id());
        image.setVariant_id(imageRequest.getVariant_id());
        image.setUpdated_at(new Date(System.currentTimeMillis()));

        Image update = imageRepo.save(image);

        return new ImageRequest(
                update.getId(),
                update.getProduct_id(),
                update.getVariant_id(),
                update.getImage_url()
        );
    }
}
