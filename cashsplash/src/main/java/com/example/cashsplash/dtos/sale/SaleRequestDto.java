package com.example.cashsplash.dtos.sale;

import com.example.cashsplash.dtos.ItemDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SaleRequestDto {
    private Long idUser;
    private Long idCustomer;
    private List<ItemDto> items;
    private Long idCampaign;
}
