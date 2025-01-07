package com.sapo.store_management.repository;

import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.ProductTag;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagRepo extends JpaRepository<ProductTag, Integer> {
    @NotNull
    Page<ProductTag> findAll(@NotNull Pageable pageable);

}
