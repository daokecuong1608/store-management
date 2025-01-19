package com.sapo.store_management.dto.order;

import java.time.Instant;
import java.util.List;

import com.sapo.store_management.dto.ProductDTO;
import com.sapo.store_management.dto.ProductRes;
import com.sapo.store_management.dto.UserDTO;
import com.sapo.store_management.dto.customer.CustomerDTO;
import com.sapo.store_management.model.Customer;
import com.sapo.store_management.model.OrderProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customer_id;

    private int coupon_id;

    private int staff_id;

    private String code;

    private int total;

    private int payment;

    private int change_given;

    private String status;

    private String note;

    private Instant created_at;

    private Instant updated_at;

    private List<ProductRes> orderProducts;

    private CustomerDTO customer;

    private UserDTO staff;

}
