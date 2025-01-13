package com.sapo.store_management.repository;

import com.sapo.store_management.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @NotNull
    Page<Product> findAll(@NotNull Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "JOIN p.tags t " +
            "WHERE t.name = :tagName")
    List<Product> findByTagName(@Param("tagName") String tagName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:productName)")
    Page<Product> findByProductName(@Param("productName")  String productName , Pageable pageable);
}
