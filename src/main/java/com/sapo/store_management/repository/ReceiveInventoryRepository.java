package com.sapo.store_management.repository;

import com.sapo.store_management.model.ReceiveInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveInventoryRepository extends 
    JpaRepository<ReceiveInventory, Integer>,
    JpaSpecificationExecutor<ReceiveInventory> {
} 