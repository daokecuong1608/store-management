package com.sapo.store_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Product code cannot be blank")
    @Size(max = 50, message = "Product code cannot exceed 50 characters")
    private String code;

    @NotNull(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private int price;

    @NotNull(message = "Capital price cannot be null")
    @PositiveOrZero(message = "Capital price must be greater than or equal to 0")
    private int capital_price;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String image;

    @NotNull(message = "Status cannot be blank")
    private String status;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_date;
}
