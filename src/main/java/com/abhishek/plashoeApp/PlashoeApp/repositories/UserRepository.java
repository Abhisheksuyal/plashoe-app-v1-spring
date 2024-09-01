package com.abhishek.plashoeApp.PlashoeApp.repositories;

import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String username);
}
