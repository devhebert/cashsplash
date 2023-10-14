package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.CampaignConverter;
import com.example.cashsplash.dtos.campanha.CampaignRequestDTO;
import com.example.cashsplash.models.Campaign;

import com.example.cashsplash.repositories.CampaignRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.cashsplash.dtos.campanha.CampaignResponseDTO;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("campaigns")
public class CampaignController {

    private final CampaignRepository repository;
    private final CampaignConverter campaignConverter;
    private final ModelMapper modelMapper;

    public CampaignController(CampaignRepository repository, CampaignConverter campaignRepo, ModelMapper modelMapper) {
        this.repository = repository;
        this.campaignConverter = campaignRepo;
        this.modelMapper = modelMapper;
    }

    private CampaignResponseDTO convertResponse(Campaign campaign) {
        return this.modelMapper.map(campaign, CampaignResponseDTO.class);
    }
    @GetMapping("/listar-todos")
    public List<Campaign> listarTodas(){
        return this.repository.findAll();
    }

    @GetMapping("/{uuid}")
    public CampaignResponseDTO getPorId(@PathVariable UUID uuid){
        Campaign campaign = this.repository.findByUuid(uuid).orElseThrow();
        return this.campaignConverter.entityToResponseDTO(campaign);
    }

    @GetMapping(params = {"nome"})
    public List<CampaignResponseDTO> buscarPorNome(@RequestParam String nome){
        return this.repository.findByNameContainingIgnoreCase(nome).stream()
                .map(this::convertResponse).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignResponseDTO createCampaign(@RequestBody CampaignRequestDTO request) {
        Campaign campaign = this.campaignConverter.requestDTOToEntity(request);
        campaign.setUuid(UUID.randomUUID());
        Campaign novaCampaign = this.repository.save(campaign);

        return this.campaignConverter.entityToResponseDTO(novaCampaign);

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CampaignResponseDTO replaceCampaign(@PathVariable UUID uuid, @RequestBody @Valid CampaignRequestDTO request) {
        Campaign campaign = this.repository.findByUuid(uuid).orElseThrow();
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setOffValue(request.getOffValue());
        Campaign replacedCampaign = this.repository.save(campaign);
        return this.campaignConverter.entityToResponseDTO(replacedCampaign);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@PathVariable UUID uuid) {
        Campaign campaign = this.repository.findByUuid(uuid).orElseThrow();
        this.repository.delete(campaign);
    }




}
