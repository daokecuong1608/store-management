package com.sapo.store_management.dto.image;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageResponse {
private Integer id;
private String imageUrl;
}
