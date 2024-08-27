package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.advice.ApiResponse;
import com.abhishek.plashoeApp.PlashoeApp.dto.SignupDTO;
import com.abhishek.plashoeApp.PlashoeApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> signup(@RequestBody SignupDTO signupDTO){
        return ResponseEntity.ok(new ApiResponse<>(userService.signup(signupDTO)));
    }

}
