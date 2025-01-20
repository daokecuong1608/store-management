package com.sapo.store_management.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receive_inventories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String code;
    private String status;
    private String note;
    private Double totalMoney;
    private Double totalQuantity;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "receiveInventory", cascade = CascadeType.ALL)
    private List<ReceiveProduct> products;
} 