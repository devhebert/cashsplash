package com.example.cashsplash.services;

import com.example.cashsplash.models.Campaign;
import com.example.cashsplash.repositories.CampaignRepository;
import com.example.cashsplash.services.campaign.CampaignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CampainServiceTest {

    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @Test
    void saveCampaing_Success() {
        Long campaignId = 1L;
        Campaign campaign = new Campaign();

        campaign.setId(campaignId);
        campaign.setName("Black-Frauday");
        campaign.setDescription("Mês do golpe");
        campaign.setOffValue(BigDecimal.valueOf(100.0));

        when(campaignRepository.save(campaign)).thenReturn(campaign);

        Optional<Campaign> savedCampaign = campaignService.saveCampaign(campaign);

        Assertions.assertTrue(savedCampaign.isPresent());
        Assertions.assertNotNull(savedCampaign.get().getId());
        Assertions.assertNotNull(savedCampaign.get().getUuid());
        Assertions.assertEquals(savedCampaign.get().getName(), "Black-Frauday");
        Assertions.assertEquals(savedCampaign.get().getDescription(), "Mês do golpe");
        Assertions.assertEquals(savedCampaign.get().getOffValue(), 100.0);
    }

    @Test
    void saveCampaign_Failure_NullObject() {
        Optional<Campaign> savedCampaign = campaignService.saveCampaign(null);
        Assertions.assertTrue(savedCampaign.isEmpty());
    }

    @Test
    void saveCampaign_Failure_InvalidData() {
        Campaign invalidCampaign = new Campaign();
        invalidCampaign.setName("");
        invalidCampaign.setDescription("test@example.com");
        invalidCampaign.setOffValue(BigDecimal.valueOf(100.0));

        Optional<Campaign> savedCampaign = campaignService.saveCampaign(invalidCampaign);
        Assertions.assertTrue(savedCampaign.isEmpty());
    }


}