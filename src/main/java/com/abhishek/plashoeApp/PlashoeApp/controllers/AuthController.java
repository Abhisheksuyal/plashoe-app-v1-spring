package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.advice.ApiResponse;
import com.abhishek.plashoeApp.PlashoeApp.dto.LoginDTO;
import com.abhishek.plashoeApp.PlashoeApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginDTO loginDTO){
        log.info("login before token line");
        String token = authService.login(loginDTO);
        log.info("login after token line");
        return new ResponseEntity<>( new ApiResponse<>(token), HttpStatus.CREATED);
    }

}
