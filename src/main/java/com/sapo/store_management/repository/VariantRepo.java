package com.sapo.store_management.repository;

import com.sapo.store_management.model.Value;
import com.sapo.store_management.model.Variant;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepo extends JpaRepository<Variant, Integer> {
    @NotNull
    Page<Variant> findAll(@NotNull Pageable pageable);

}
