package com.sapo.store_management.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private int customer_id;

    private int coupon_id;

    private int staff_id;

    private String note;

    @Min(value = 0, message = "Total phải lớn hơn hoặc bằng 0")
    private int total;

    @Min(value = 0, message = "Payment phải lớn hơn hoặc bằng 0")
    private int payment;

    @Min(value = 0, message = "Change phải lớn hơn hoặc bằng 0")
    private int change_given;

    private List<ProductRequest> products;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductRequest {
        private int productId;

        private int quantity;

        private int price;
    }

}
