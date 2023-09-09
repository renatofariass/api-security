package com.apitest.security.domain.product;

import jakarta.persistence.*;

@Table(name = "products")
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private int price;

    public Product() {
    }

    public Product(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.name();
        this.price = productRequestDto.price();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
