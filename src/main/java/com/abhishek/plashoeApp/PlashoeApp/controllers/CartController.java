package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.dto.CartDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.ProductDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.StringDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.services.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
   private final ModelMapper modelMapper;

    @PostMapping("/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long productId){
        cartService.addProductToCart(productId);
        StringDTO stringDTO = new StringDTO();
      stringDTO.setResponse("product added ");
        return ResponseEntity.ok(stringDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDTO>> getAllProductsFromCart(@PathVariable Long userId,@RequestParam(defaultValue = "0") int pageNumber){
       return ResponseEntity.ok(cartService.getAllProductsFromCart(pageNumber,userId));
    }

}
