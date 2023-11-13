package com.subproblem.securityservice.repository;

import com.subproblem.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM User user where user.email = :email")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}