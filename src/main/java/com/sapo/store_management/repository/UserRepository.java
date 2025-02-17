package com.sapo.store_management.repository;

import com.sapo.store_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer > {
    Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);

}
