package com.sapo.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTagRequest {
    private int id;
    private int product_id;
    private int tag_id;
}
