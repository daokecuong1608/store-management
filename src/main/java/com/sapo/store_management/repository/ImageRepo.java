package com.sapo.store_management.repository;

import com.sapo.store_management.model.Category;
import com.sapo.store_management.model.Image;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

    @NotNull
    Page<Image> findAll(@NotNull Pageable pageable);

}
