package com.example.cashsplash.dtos.campanha;

import com.example.cashsplash.models.Campanha;

import java.math.BigDecimal;
import java.util.UUID;

public record CampaignResponseDTO(UUID uuid, String nome, String descricao, BigDecimal valor) {
    public CampaignResponseDTO(Campanha campaign){
        this(campaign.getUuid(), campaign.getNome(), campaign.getDescricao(), campaign.getValor());

    }
}
