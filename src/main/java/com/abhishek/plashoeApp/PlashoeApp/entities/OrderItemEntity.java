package com.abhishek.plashoeApp.PlashoeApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    private Double subTotal;

}
