package com.sapo.store_management.repository;

import com.sapo.store_management.model.Tag;
import com.sapo.store_management.model.Value;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepo extends JpaRepository<Value, Integer> {
    @NotNull
    Page<Value> findAll(@NotNull Pageable pageable);

}
