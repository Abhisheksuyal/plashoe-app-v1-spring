package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.advice.ApiResponse;
import com.abhishek.plashoeApp.PlashoeApp.dto.OrderDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.RazorpayOrderDTO;
import com.abhishek.plashoeApp.PlashoeApp.services.RazorpayOrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final RazorpayOrderService razorpayOrderService;

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<ApiResponse<?>> createOrder() throws RazorpayException {


        Order razorpayOrder = razorpayOrderService.createOrder();
        RazorpayOrderDTO razorpayOrderDTO = new RazorpayOrderDTO();
        razorpayOrderDTO.setId(razorpayOrder.get("id"));
        razorpayOrderDTO.setAmount(razorpayOrder.get("amount"));
        razorpayOrderDTO.setStatus(razorpayOrder.get("status"));
        razorpayOrderDTO.setCurrency(razorpayOrder.get("currency"));
        razorpayOrderDTO.setEntity(razorpayOrder.get("entity"));
        return ResponseEntity.ok(new ApiResponse<>(razorpayOrderDTO));
    }



}
