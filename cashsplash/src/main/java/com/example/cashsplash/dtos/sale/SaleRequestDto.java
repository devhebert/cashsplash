package com.example.cashsplash.dtos.sale;

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
    private List<Long> idProducts;
    private Long idCampaign;
}
