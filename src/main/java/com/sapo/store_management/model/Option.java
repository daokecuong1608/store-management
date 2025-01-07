package com.sapo.store_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@RequiredArgsConstructor
@Table(name="options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int product_id;

    private String name;

//    @ElementCollection
//    private List<String> values;

    private int position;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
