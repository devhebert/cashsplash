package com.example.cashsplash.converters;

import com.example.cashsplash.dtos.campanha.CampaignRequestDTO;
import com.example.cashsplash.dtos.campanha.CampaignResponseDTO;
import com.example.cashsplash.models.Campaign;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {
    private final ModelMapper modelMapper;
    public CampaignConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Campaign requestDTOToEntity(CampaignRequestDTO request) {
        return this.modelMapper.map(request, Campaign.class);
    }

    public Campaign responseDTOToEntity(CampaignResponseDTO response) {
        return this.modelMapper.map(response, Campaign.class);
    }

    public CampaignRequestDTO entityToRequestDTO(Campaign campaign) {
        return this.modelMapper.map(campaign, CampaignRequestDTO.class);
    }

    public CampaignResponseDTO entityToResponseDTO(Campaign campaign) {
        return this.modelMapper.map(campaign, CampaignResponseDTO.class);
    }

}
