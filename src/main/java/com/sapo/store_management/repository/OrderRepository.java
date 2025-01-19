package com.sapo.store_management.repository;

import org.springframework.stereotype.Repository;

import com.sapo.store_management.model.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @NonNull
    Page<Order> findAll(@NonNull Pageable pageable);

    Order findByCode(String code);

    @Query("SELECT o FROM Order o WHERE o.code = :query OR o.customer.phone = :query")
    Page<Order> searchByCodeOrCustomerPhone(@Param("query") String query, @NonNull Pageable pageable);

}
