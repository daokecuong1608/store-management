package com.sapo.store_management.service.img;

import com.sapo.store_management.dto.image.ImgBBResponse;
import com.sapo.store_management.model.Image;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.repository.ImageRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Autowired
    private ImgBBService imgBBService;

    @Autowired
    private ImageRepo imageRepository;

    public Image uploadAndSaveImage(MultipartFile file , Product product) {
        // Upload image to ImgBB and get image URL
        ImgBBResponse response = imgBBService.uploadImageToImgBB(file);
        Image image = new Image();
        image.setImageUrl(response.getData().getUrl());
        image.setProduct(product);

        return imageRepository.save(image);
    }
//    public Image uploadAndSaveImage_2(MultipartFile file ) {
//        // Upload image to ImgBB and get image URL
//        String imageUrl = imgBBService.uploadImageToImgBB(file);
//        // Save image information to database
//        Image image = new Image();
//        image.setImageUrl(imageUrl);
//        return imageRepository.save(image);
//    }
}
