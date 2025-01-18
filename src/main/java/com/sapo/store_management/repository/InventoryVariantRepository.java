package com.sapo.store_management.repository;

import com.sapo.store_management.model.InventoryVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryVariantRepository extends JpaRepository<InventoryVariant, Integer> {

}
