package com.sapo.store_management.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customer_id;

    private int coupon_id;

    private int staff_id;

    private String code;

    @Min(value = 0, message = "Total phải lớn hơn hoặc bằng 0")
    private int total;

    @Min(value = 0, message = "Payment phải lớn hơn hoặc bằng 0")
    private int payment;

    @Min(value = 0, message = "Change phải lớn hơn hoặc bằng 0")
    private int change_given;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Instant created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Instant updated_at;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderProduct> orderProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Customer customer;

    @PrePersist
    public void handleBeforeCreate() {
        this.created_at = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updated_at = Instant.now();
    }
}
