package com.sapo.store_management.repository;

import com.sapo.store_management.model.Brand;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    @NotNull
    Page<Brand> findAll(@NotNull Pageable pageable);

    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) LIKE LOWER(:brandName)")
    Page<Brand> findByNameBrand(@Param("brandName") String brandName, Pageable pageable);


}
