package com.example.cashsplash.dtos.campanha;

import com.example.cashsplash.models.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponseDTO {

    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private BigDecimal offValue;

    private boolean active;

    public CampaignResponseDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.uuid = campaign.getUuid();
        this.name = campaign.getName();
        this.description = campaign.getDescription();
        this.offValue = campaign.getOffValue();
        this.active = campaign.isActive();
    }
}
