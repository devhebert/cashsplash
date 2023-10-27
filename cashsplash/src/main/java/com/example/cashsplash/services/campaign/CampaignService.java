package com.example.cashsplash.services.campaign;

import com.example.cashsplash.models.Campaign;
import com.example.cashsplash.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }
    public List<Campaign> findAll() {
        return this.campaignRepository.findAll();
    }
    public Optional<Campaign> findByUuid(UUID uuid) {
        return this.campaignRepository.findByUuid(uuid);
    }
    public Optional<Campaign> findByNameContainingIgnoreCase(String nome) {return this.campaignRepository.findByNameContainingIgnoreCase(nome);}
    public Optional<Campaign> updateCampaign(UUID uuid, Campaign campanha) {
        Optional<Campaign> existingCampaign = this.findByUuid(uuid);

        if (existingCampaign.isEmpty()) {
            return Optional.empty();
        }

        Campaign campaign = existingCampaign.get();

        campaign.setName(campanha.getName() != null ? campanha.getName() : campaign.getName());
        campaign.setDescription(campanha.getDescription() != null ? campanha.getDescription() : campaign.getDescription());
        campaign.setUuid(campanha.getUuid() != null ? campanha.getUuid() : campaign.getUuid());
        campaign.setOffValue(campanha.getOffValue() != null ? campanha.getOffValue() : campaign.getOffValue());

        Campaign updatedCampaign = this.campaignRepository.save(campaign);
        return Optional.of(updatedCampaign);
    }

    public boolean deleteCampaign(UUID uuid) {
        Optional<Campaign> existingCampaign = this.campaignRepository.findByUuid(uuid);

        if (existingCampaign.isEmpty()) {
        return false;
        }

        this.campaignRepository.delete(existingCampaign.get());
        return true;
    }

    public Optional<Campaign> saveCampaign(Campaign data) {
        boolean isValidCampaign = validateCampaign(data);

        if (!isValidCampaign) return Optional.empty();

        Optional<Campaign> existingCampaign = this.campaignRepository.findByNameContainingIgnoreCase(data.getName());
        if (existingCampaign.isPresent()) {
            return Optional.empty();
        }

        data.setUuid(UUID.randomUUID());
        Campaign savedCampaign = this.campaignRepository.save(data);
        return Optional.of(savedCampaign);
    }

    private boolean validateCampaign(Campaign campaign) {
        if (campaign == null) return false;

        return campaign.getName() != null && !campaign.getName().trim().isEmpty() &&
                campaign.getDescription() != null && !campaign.getDescription().trim().isEmpty() &&
                campaign.getOffValue() != null;
    }


    public Campaign getActiveCampaign() {

        List<Campaign> allCampaigns = campaignRepository.findAll();

        if (!allCampaigns.isEmpty()) {
            return allCampaigns.get(0);
        } else {
            return null;
        }
    }
}