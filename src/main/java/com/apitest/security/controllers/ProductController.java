package com.apitest.security.controllers;

import com.apitest.security.domain.product.Product;
import com.apitest.security.domain.product.ProductRequestDto;
import com.apitest.security.domain.product.ProductResponseDTO;
import com.apitest.security.repositories.ProductRepository;
import com.apitest.security.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductRepository repository;
    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Void> postProduct(@RequestBody @Valid ProductRequestDto data){
        Product newProduct = new Product(data);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productList = service.findAll();

        return ResponseEntity.ok(productList);
    }
}
