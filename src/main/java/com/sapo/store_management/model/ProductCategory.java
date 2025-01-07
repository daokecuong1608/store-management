package com.sapo.store_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be a positive integer")
    private int product_id;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive integer")
    private int category_id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
}
