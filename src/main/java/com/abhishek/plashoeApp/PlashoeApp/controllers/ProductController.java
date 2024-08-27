package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.dto.ProductDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.ProductEntity;
import com.abhishek.plashoeApp.PlashoeApp.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO product){
        return productService.createProduct(product);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody Map<String,Object> updates){
               return productService.updateProduct(productId,updates);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNumber){
        return productService.getAllProducts(pageNumber);
    }


}
