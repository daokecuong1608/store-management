package com.sapo.store_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="tbl_option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = true)
    private Product product;

    @OneToMany(mappedBy = "option" , cascade = CascadeType.ALL )
    private List<Value> values;


    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at;

    @Column(name="updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist(){
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updated_at = LocalDateTime.now();
    }

}
