package com.sapo.store_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Image ID cannot be null")
    @Positive(message = "Image ID must be a positive integer")
    private int image_id;

    @Size(max = 100, message = "Option1 cannot exceed 100 characters")
    private String option1;

    @Size(max = 100, message = "Option2 cannot exceed 100 characters")
    private String option2;

    @Size(max = 100, message = "Option3 cannot exceed 100 characters")
    private String option3;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive integer")
    private int price;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
}
