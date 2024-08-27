package com.abhishek.plashoeApp.PlashoeApp.services;

import com.abhishek.plashoeApp.PlashoeApp.dto.LoginDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.SignupDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.UserDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final JwtService jwtService;
    private final  AuthenticationManager authManager;

    public String login(LoginDTO loginDTO){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
       String token= jwtService.generateJwtToken(userEntity);
       log.info("token is "+token);
       return token;


    }

}
