package com.abhishek.plashoeApp.PlashoeApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

    private ProductDTO productDTO;

    private int quantity;
    private double subTotal;

}
