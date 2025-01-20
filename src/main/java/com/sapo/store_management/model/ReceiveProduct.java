package com.sapo.store_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "receive_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String code;
    private String name;
    private String description;
    private String brand;
    private String category;
    private String createdAt;
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "receive_inventory_id")
    private ReceiveInventory receiveInventory;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
} 