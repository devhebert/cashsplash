package com.example.cashsplash.converters;

import com.example.cashsplash.dtos.campanha.CampaignRequestDTO;
import com.example.cashsplash.dtos.campanha.CampaignResponseDTO;
import com.example.cashsplash.models.Campanha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {
    private final ModelMapper modelMapper;
    public CampaignConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    private CampaignResponseDTO convertResponse(Campanha campanha) {
        return this.modelMapper.map(campanha, CampaignResponseDTO.class);
    }
    public Campanha requestDTOToEntity(CampaignRequestDTO request) {
        return this.modelMapper.map(request, Campanha.class);
    }

    public Campanha responseDTOToEntity(CampaignResponseDTO response) {
        return this.modelMapper.map(response, Campanha.class);
    }

    public CampaignRequestDTO entityToRequestDTO(Campanha campaign) {
        return this.modelMapper.map(campaign, CampaignRequestDTO.class);
    }

    public CampaignResponseDTO entityToResponseDTO(Campanha campaign) {
        return this.modelMapper.map(campaign, CampaignResponseDTO.class);
    }

}
