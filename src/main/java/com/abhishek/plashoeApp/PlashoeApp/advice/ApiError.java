package com.abhishek.plashoeApp.PlashoeApp.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus statusCode;

    public ApiError(String message,HttpStatus statusCode){
        this.timestamp=LocalDateTime.now();
        this.message=message;
        this.statusCode=statusCode;
    }

}
