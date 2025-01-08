package com.sapo.store_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapo.store_management.model.OrderProduct;

// Test
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

}
