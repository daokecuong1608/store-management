package com.sapo.store_management.repository;

import com.sapo.store_management.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @NotNull
    Page<Product> findAll(@NotNull Pageable pageable);

}
