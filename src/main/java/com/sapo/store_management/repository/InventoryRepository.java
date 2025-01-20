package com.sapo.store_management.repository;

import com.sapo.store_management.model.Inventory;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Storage;
import com.sapo.store_management.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {
    Optional<Inventory> findByProductAndStorageAndSupplier(
        Product product, 
        Storage storage, 
        Supplier supplier
    );
}
