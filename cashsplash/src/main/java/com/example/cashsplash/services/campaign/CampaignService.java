package com.example.cashsplash.services.campaign;

import com.example.cashsplash.models.Campaign;
import com.example.cashsplash.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public boolean deleteCampaign(UUID uuid) {
        Optional<Campaign> existingCampaign = this.campaignRepository.findByUuid(uuid);

        if (existingCampaign.isEmpty()) {
        return false;
        }

        campaignRepository.delete(existingCampaign.get());
        return true;
    }

}