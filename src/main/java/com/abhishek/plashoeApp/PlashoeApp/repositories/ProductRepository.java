package com.abhishek.plashoeApp.PlashoeApp.repositories;

import com.abhishek.plashoeApp.PlashoeApp.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
