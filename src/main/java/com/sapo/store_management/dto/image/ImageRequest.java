package com.sapo.store_management.dto.image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequest {


//    @NotNull(message = "Product ID cannot be null")
//    @Positive(message = "Product ID must be a positive integer")
//    private Integer productId;
//
//    @NotNull(message = "Variant ID cannot be null")
//    @Positive(message = "Variant ID must be a positive integer")
//    private Integer variantId;

    @NotNull(message = "Image file cannot be null")
    private MultipartFile file;

    @NotBlank(message = "Uploaded by cannot be blank")
    private String uploadedBy;

}
