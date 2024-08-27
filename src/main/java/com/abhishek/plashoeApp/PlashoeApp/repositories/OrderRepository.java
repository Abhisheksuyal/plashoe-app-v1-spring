package com.abhishek.plashoeApp.PlashoeApp.repositories;

import com.abhishek.plashoeApp.PlashoeApp.entities.OrderEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Optional<OrderEntity> findByRazorpayId(String id);
}
