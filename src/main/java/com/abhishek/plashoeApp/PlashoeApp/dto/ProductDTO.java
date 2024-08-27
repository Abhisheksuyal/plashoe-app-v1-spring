package com.abhishek.plashoeApp.PlashoeApp.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min=1,max=15,message="must be greater than 1")
    private String productName;

    @NotEmpty(message = "description cannot be empty")
    @Size(min = 1 , max = 80)
    private String description;

    private int quantity;

    @NotNull(message = "price cannot be empty")
    @Min(value=1)
    private double price;
}
