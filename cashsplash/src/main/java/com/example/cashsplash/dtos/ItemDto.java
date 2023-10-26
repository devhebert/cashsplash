package com.example.cashsplash.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDto {
    private Long idProduct;
    private Integer amount;
}
