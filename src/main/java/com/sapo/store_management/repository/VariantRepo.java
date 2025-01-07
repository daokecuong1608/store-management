package com.sapo.store_management.repository;

import com.sapo.store_management.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepo extends JpaRepository<Variant, Integer> {
}
