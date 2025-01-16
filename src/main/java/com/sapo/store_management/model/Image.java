package com.sapo.store_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @NotNull(message = "Product ID cannot be null")
//    @Positive(message = "Product ID must be a positive integer")
//    @Column(name = "product_id", nullable = false)
//    private Integer productId;
//
//    @NotNull(message = "Variant ID cannot be null")
//    @Positive(message = "Variant ID must be a positive integer")
//    @Column(name = "variant_id", nullable = false)
//    private Integer variantId;

    @NotBlank(message = "Image URL cannot be blank")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "uploaded_by", nullable = true)
    private String uploadedBy;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

}
