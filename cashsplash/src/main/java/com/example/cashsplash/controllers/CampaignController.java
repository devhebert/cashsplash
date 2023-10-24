package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.CampaignConverter;
import com.example.cashsplash.dtos.campanha.CampaignRequestDTO;
import com.example.cashsplash.dtos.user.UserResponseDTO;
import com.example.cashsplash.models.Campaign;

import com.example.cashsplash.repositories.CampaignRepository;
import com.example.cashsplash.services.campaign.CampaignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.cashsplash.dtos.campanha.CampaignResponseDTO;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("campaigns")
public class CampaignController {

    private final CampaignConverter campaignConverter;
    private final CampaignService campaignService;
    private final ModelMapper modelMapper;

    public CampaignController(CampaignConverter campaignRepo, CampaignService campaignService, ModelMapper modelMapper) {
        this.campaignConverter = campaignRepo;
        this.campaignService = campaignService;
        this.modelMapper = modelMapper;
    }

    private CampaignResponseDTO convertResponse(Campaign campaign) {
        return this.modelMapper.map(campaign, CampaignResponseDTO.class);
    }
    @GetMapping("/listar-todos")
    public List<Campaign> listarTodas(){
        return this.campaignService.findAll();
    }

    @GetMapping("/{uuid}")
    public CampaignResponseDTO getPorId(@PathVariable UUID uuid){
        Campaign campaign = this.campaignService.findByUuid(uuid).orElseThrow();
        return this.campaignConverter.entityToResponseDTO(campaign);
    }

    @GetMapping(params = {"nome"})
    public List<CampaignResponseDTO> buscarPorNome(@RequestParam String nome){
        return this.campaignService.findByNameContainingIgnoreCase(nome).stream()
                .map(this::convertResponse).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CampaignResponseDTO> createCampaign(@RequestBody @Valid CampaignRequestDTO request) {
        Campaign campaignEntity = this.campaignConverter.requestDTOToEntity(request);
        Optional<Campaign> savedCampaign = this.campaignService.saveCampaign(campaignEntity);

        if (savedCampaign.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        CampaignResponseDTO responseDTO = this.campaignConverter.entityToResponseDTO(savedCampaign.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CampaignResponseDTO> replaceCampaign(@PathVariable UUID uuid, @RequestBody @Valid CampaignRequestDTO request) {
        Campaign campaignEntity = this.campaignConverter.requestDTOToEntity(request);
        Optional<Campaign> replacedCampaign = this.campaignService.updateCampaign(uuid, campaignEntity);

        if (replacedCampaign.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        CampaignResponseDTO responseDTO = this.campaignConverter.entityToResponseDTO(replacedCampaign.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDTO);

    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CampaignResponseDTO> deleteCampaign(@PathVariable UUID uuid) {
        boolean deleted = campaignService.deleteCampaign(uuid);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
