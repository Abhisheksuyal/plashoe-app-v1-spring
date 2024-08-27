package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.advice.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> healthCheckController(){
        return ResponseEntity.ok(new ApiResponse<>("Application is up and running"));
    }
}
