package com.sapo.store_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapo.store_management.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
