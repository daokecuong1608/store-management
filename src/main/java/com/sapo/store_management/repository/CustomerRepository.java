package com.sapo.store_management.repository;

import com.sapo.store_management.model.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFullnameContainingOrPhoneContaining(String fullname, String phone);

    Optional<Customer> findById(int id);
}