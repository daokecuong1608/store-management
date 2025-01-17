package com.sapo.store_management.service.img;

import com.sapo.store_management.model.Image;
import com.sapo.store_management.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Autowired
    private ImgBBService imgBBService;

    @Autowired
    private ImageRepo imageRepository;

    public Image uploadAndSaveImage(MultipartFile file, String uploadedBy) {
        // Upload image to ImgBB and get image URL
        String imageUrl = imgBBService.uploadImageToImgBB(file);

        // Save image information to database
        Image image = new Image();
        image.setImageUrl(imageUrl);
        image.setUploadedBy(uploadedBy);
        image.setStatus("uploaded");

        return imageRepository.save(image);
    }
}
