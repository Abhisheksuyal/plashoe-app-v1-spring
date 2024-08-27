package com.abhishek.plashoeApp.PlashoeApp.services;

import com.abhishek.plashoeApp.PlashoeApp.dto.SignupDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.UserDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public SignupDTO signup(SignupDTO signupDTO){

       Optional<UserEntity> user = userRepository.findByEmail(signupDTO.getEmail());

       if(user.isPresent()){
           throw new BadCredentialsException("user with email already exists");
       }

        UserEntity userEntity = modelMapper.map(signupDTO,UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return modelMapper.map(userRepository.save(userEntity), SignupDTO.class);

    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("user with id not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        return userRepository.findByEmail(username).orElseThrow(()-> new NoSuchElementException("user with provided username not found"));
    }
}
