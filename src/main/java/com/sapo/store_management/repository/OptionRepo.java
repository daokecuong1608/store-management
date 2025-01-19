package com.sapo.store_management.repository;

import com.sapo.store_management.model.Image;
import com.sapo.store_management.model.Option;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends JpaRepository<Option, Integer> {
    @NotNull
    Page<Option> findAll(@NotNull Pageable pageable);

}
