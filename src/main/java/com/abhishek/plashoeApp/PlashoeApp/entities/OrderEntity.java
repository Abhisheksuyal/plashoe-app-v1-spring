package com.abhishek.plashoeApp.PlashoeApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime orderTimestamp;

    private String shippingAddress;

    private Long phoneNumber;

    private double total;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<OrderItemEntity> orderItems;

    private String orderStatus;

    private String razorpayId;


}
