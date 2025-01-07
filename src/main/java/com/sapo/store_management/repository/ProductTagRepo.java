package com.sapo.store_management.repository;

import com.sapo.store_management.model.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagRepo extends JpaRepository<ProductTag, Integer> {
}
