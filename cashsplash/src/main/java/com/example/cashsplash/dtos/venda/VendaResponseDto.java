package com.example.cashsplash.dtos.venda;

import com.example.cashsplash.models.*;

import java.util.UUID;

public record VendaResponseDto(Long id, UUID uuid, User idUsuario, Cliente idCliente, Produto idProduto, Campaign idCampaign) {

    public VendaResponseDto(Venda venda) {
        this(venda.getId(), venda.getUuid(), venda.getIdUsuario(), venda.getIdCliente(), venda.getIdProduto(), venda.getIdCampaign());
    }
}
