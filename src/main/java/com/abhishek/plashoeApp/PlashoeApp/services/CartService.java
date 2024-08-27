package com.abhishek.plashoeApp.PlashoeApp.services;

import com.abhishek.plashoeApp.PlashoeApp.dto.CartDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.ProductDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.SignupDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.UserDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.CartEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.ProductEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.repositories.CartRepository;
import com.abhishek.plashoeApp.PlashoeApp.repositories.ProductRepository;
import com.abhishek.plashoeApp.PlashoeApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;
    private final int PAGE_SIZE=6;

    public void addProductToCart(Long productId){
        UserEntity user = userRepository.findById(1L).orElse(null);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("product not found"));

        CartEntity cartEntity = new CartEntity();
        cartEntity.setProduct(productEntity);
        cartEntity.setUser(user);
        cartEntity.setQuantity(1);
        cartEntity.setSubTotal(productEntity.getPrice());

        cartRepository.save(cartEntity);


    }

    public List<CartDTO> getAllProductsFromCart(int pageNumber, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User cannot found"));
        List<CartEntity> cartEntities =  cartRepository.findByUser(userEntity,PageRequest.of(pageNumber,PAGE_SIZE));
        return cartEntities.stream().map(cart -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductDTO(modelMapper.map(cart.getProduct(),ProductDTO.class));
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setSubTotal(cart.getSubTotal());
            return cartDTO;
        }).collect(Collectors.toList());

    }
}
