package com.example.cashsplash.dtos.product;

import com.example.cashsplash.models.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID uuid, String name, String description, BigDecimal value, Integer amount) {
    public ProductResponseDTO(Product product) {
        this(product.getUuid(), product.getName(), product.getDescription(), product.getValue(), product.getAmount());
    }
}
