package com.abhishek.plashoeApp.PlashoeApp.services;

import com.abhishek.plashoeApp.PlashoeApp.dto.ProductDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.ProductEntity;
import com.abhishek.plashoeApp.PlashoeApp.repositories.ProductRepository;

import com.abhishek.plashoeApp.PlashoeApp.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final int PAGE_SIZE = 6;

    public ResponseEntity<ProductDTO> createProduct(ProductDTO product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

        ProductDTO savedDTO = modelMapper.map(productRepository.save(productEntity), ProductDTO.class);

        return ResponseEntity.ok(savedDTO);

    }

    public ResponseEntity<ProductDTO> updateProduct(Long productId, Map<String, Object> updates) {
            ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("cannot find product"));
            UpdateUtils.applyUpdates(productEntity,updates);
         return ResponseEntity.ok(modelMapper.map(productRepository.save(productEntity), ProductDTO.class));
    }


    public List<ProductDTO> getAllProducts(int pageNumber) {
        return productRepository.findAll(PageRequest.of(pageNumber,PAGE_SIZE)).stream().
                map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }
}

