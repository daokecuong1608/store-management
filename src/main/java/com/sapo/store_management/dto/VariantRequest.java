package com.sapo.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VariantRequest {
    private int id;
    private int image_id;
    private String option1;
    private String option2;
    private String option3;
    private int price;
}
