package com.sapo.store_management.dto.image;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImageRequest {
    @NotNull(message = "Image file cannot be null")
    public String url;
}
