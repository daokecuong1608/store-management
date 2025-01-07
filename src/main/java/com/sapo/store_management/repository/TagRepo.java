package com.sapo.store_management.repository;

import com.sapo.store_management.model.ProductTag;
import com.sapo.store_management.model.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {
    @NotNull
    Page<Tag> findAll(@NotNull Pageable pageable);

}
