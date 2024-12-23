package com.eventmanagement.repository;

import com.eventmanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepo extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String authId);
}