package com.sapo.store_management.service;

import com.sapo.store_management.dto.ImageRequest;
import com.sapo.store_management.model.Category;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.repository.ImageRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public Page<Image> getAllImages(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Image> result = imageRepo.findAll(pageable);
        return result;
    }

    public Image getImageById(int id) {
        Image image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find image"));
        return image;
    }

    public Image insertImage(Image image) {
        Image insertImage = imageRepo.save(image);
        return insertImage;
    }


    public void deleteImage(int id) {
        Image image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        imageRepo.delete(image);
    }

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
