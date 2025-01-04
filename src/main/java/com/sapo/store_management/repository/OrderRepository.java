package com.sapo.store_management.repository;

import org.springframework.stereotype.Repository;

import com.sapo.store_management.model.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @NonNull
    Page<Order> findAll(@NonNull Pageable pageable);
}
