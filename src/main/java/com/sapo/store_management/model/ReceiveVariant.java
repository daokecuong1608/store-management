package com.sapo.store_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "receive_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String sku;
    private Integer quantity;
    private Double price;
    private String unit;
    private String createdAt;
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "receive_product_id")
    private ReceiveProduct receiveProduct;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
} 