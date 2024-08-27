package com.abhishek.plashoeApp.PlashoeApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RazorpayOrderDTO {

    private int amount;
    private int amount_due;
    private String currency;
    private String id;
    private String status;
    private String entity;

}
