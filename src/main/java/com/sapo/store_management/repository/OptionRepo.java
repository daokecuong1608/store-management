package com.sapo.store_management.repository;

import com.sapo.store_management.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends JpaRepository<Option, Integer> {
}
