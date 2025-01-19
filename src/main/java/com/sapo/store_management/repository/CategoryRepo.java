package com.sapo.store_management.repository;

import com.sapo.store_management.model.Brand;
import com.sapo.store_management.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @NotNull
    Page<Category> findAll(@NotNull Pageable pageable);

}
