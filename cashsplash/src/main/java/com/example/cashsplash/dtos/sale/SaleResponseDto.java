package com.example.cashsplash.dtos.sale;

import com.example.cashsplash.models.*;

import java.util.UUID;

public record SaleResponseDto(Long id, UUID uuid, User idUsuario, Customer idCustomer, Product idProduct, Campaign idCampaign) {

    public SaleResponseDto(Sale sale) {
        this(sale.getId(), sale.getUuid(), sale.getIdUser(), sale.getIdCustomer(), sale.getIdProduct(), sale.getIdCampaign());
    }
}
