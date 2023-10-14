package com.example.cashsplash.dtos.campanha;

import com.example.cashsplash.models.Campaign;

import java.math.BigDecimal;
import java.util.UUID;

public record CampaignResponseDTO(UUID uuid, String nome, String descricao, BigDecimal valor) {
    public CampaignResponseDTO(Campaign campaign){
        this(campaign.getUuid(), campaign.getName(), campaign.getDescription(), campaign.getOffValue());

    }
}
