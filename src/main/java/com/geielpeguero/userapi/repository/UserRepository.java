package com.geielpeguero.userapi.repository;

import com.geielpeguero.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
    User findByEmail(String email);
}
