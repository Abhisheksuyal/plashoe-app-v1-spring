package com.abhishek.plashoeApp.PlashoeApp.repositories;

import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    UserEntity userEntity;

    @BeforeEach
    void setup(){
        userEntity = new UserEntity(1L,"test","test@gmail.com","1234","india",987654322);
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnUser(){

        userRepository.save(userEntity);

        UserEntity user = userRepository.findByEmail(userEntity.getEmail()).orElse(null);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getEmail()).isEqualTo(userEntity.getEmail());

    }


    void testFindByEmail_whenEmailIsNotPresent_thenReturnNotFound(){
            userRepository.save(userEntity);

            UserEntity user = userRepository.findByEmail("notfound@gmail.com").orElse(null);



    }



}
