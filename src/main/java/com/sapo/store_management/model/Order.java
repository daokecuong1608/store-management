package com.sapo.store_management.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "`order`")
//@Getter
//@Setter
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

    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Instant created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Instant updated_at;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderProduct> orderProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = true, insertable = false, updatable = false)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Customer customer;

    @PrePersist
    public void handleBeforeCreate() {
        this.created_at = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updated_at = Instant.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Min(value = 0, message = "Total phải lớn hơn hoặc bằng 0")
    public int getTotal() {
        return total;
    }

    public void setTotal(@Min(value = 0, message = "Total phải lớn hơn hoặc bằng 0") int total) {
        this.total = total;
    }

    @Min(value = 0, message = "Payment phải lớn hơn hoặc bằng 0")
    public int getPayment() {
        return payment;
    }

    public void setPayment(@Min(value = 0, message = "Payment phải lớn hơn hoặc bằng 0") int payment) {
        this.payment = payment;
    }

    @Min(value = 0, message = "Change phải lớn hơn hoặc bằng 0")
    public int getChange_given() {
        return change_given;
    }

    public void setChange_given(@Min(value = 0, message = "Change phải lớn hơn hoặc bằng 0") int change_given) {
        this.change_given = change_given;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
