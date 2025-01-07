package com.sapo.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequest {
    private int id;
    private int product_id;
    private int variant_id;
    private String image_url;
}
