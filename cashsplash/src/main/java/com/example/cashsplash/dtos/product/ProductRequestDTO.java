package com.example.cashsplash.dtos.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal value;
    private Integer amount;
}