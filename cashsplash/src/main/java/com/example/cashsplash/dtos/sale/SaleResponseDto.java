package com.example.cashsplash.dtos.sale;

import com.example.cashsplash.dtos.ItemDto;
import com.example.cashsplash.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDto {
    private Long id;
    private UUID uuid;
    private User user;
    private Customer customer;
    private Campaign campaign;
    private List<ItemDto> items;
}
