package com.sapo.store_management.repository;

import com.sapo.store_management.model.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFullnameContainingOrPhoneContaining(String fullname, String phone);

    Optional<Customer> findById(Integer id);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders")
    List<Customer> findAllCustomersWithOrders();

    public boolean existsByPhone(String phone);
}
