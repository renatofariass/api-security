package com.apitest.security.services;

import com.apitest.security.domain.product.Product;
import com.apitest.security.domain.product.ProductRequestDto;
import com.apitest.security.domain.product.ProductResponseDTO;
import com.apitest.security.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    public void insert(ProductRequestDto data) {
        Product product = new Product(data);

        repository.save(product);
    }
}
