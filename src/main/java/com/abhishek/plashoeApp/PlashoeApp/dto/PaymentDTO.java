package com.abhishek.plashoeApp.PlashoeApp.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String orderId;
    private String paymentId;
    private String signature;
}
