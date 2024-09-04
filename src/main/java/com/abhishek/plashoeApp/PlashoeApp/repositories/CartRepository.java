package com.abhishek.plashoeApp.PlashoeApp.repositories;

import com.abhishek.plashoeApp.PlashoeApp.entities.CartEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {
    List<CartEntity> findByUser(UserEntity userEntity, Pageable pageable);
    List<CartEntity> findByUser(UserEntity userEntity);
}
