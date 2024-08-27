package com.abhishek.plashoeApp.PlashoeApp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNo;
}
