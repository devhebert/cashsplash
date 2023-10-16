package com.example.cashsplash.dtos.sale;

import com.example.cashsplash.models.Campaign;
import com.example.cashsplash.models.Customer;
import com.example.cashsplash.models.Product;
import com.example.cashsplash.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleRequestDto {
    private User idUsuario;
    private Customer customer;
    private Product idProduct;
    private Campaign idCampaign;
}
