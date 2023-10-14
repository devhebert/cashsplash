package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.CampaignConverter;
import com.example.cashsplash.dtos.campanha.CampaignRequestDTO;
import com.example.cashsplash.services.campaign.CampaignService;
import com.example.cashsplash.models.Campanha;

import com.example.cashsplash.repositories.CampaignRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.cashsplash.dtos.campanha.CampaignResponseDTO;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("campaigns")
public class CampaignController {

    private final CampaignRepository repository;
    private final CampaignConverter campaignConverter;
    private final ModelMapper modelMapper;
    private final CampaignService campaignService;

    public CampaignController(CampaignRepository repository, CampaignConverter campaignRepo, ModelMapper modelMapper, CampaignService campaignService) {
        this.repository = repository;
        this.campaignConverter = campaignRepo;
        this.modelMapper = modelMapper;
        this. campaignService = campaignService;
    }

    private CampaignResponseDTO convertResponse(Campanha campanha) {
        return this.modelMapper.map(campanha, CampaignResponseDTO.class);
    }
    @GetMapping("/listar-todos")
    public List<Campanha> listarTodas(){
        return this.repository.findAll();
    }

    @GetMapping("/{uuid}")
    public CampaignResponseDTO getPorId(@PathVariable UUID uuid){
        Campanha campanha = this.repository.findByUuid(uuid).orElseThrow();
        return this.campaignConverter.entityToResponseDTO(campanha);
    }

    @GetMapping(params = {"nome"})
    public List<CampaignResponseDTO> buscarPorNome(@RequestParam String nome){
        return this.repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::convertResponse).toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignResponseDTO createCampaign(@RequestBody CampaignRequestDTO request) {
        Campanha campanha = this.campaignConverter.requestDTOToEntity(request);
        Campanha novaCampanha = this.repository.save(campanha);

        return this.campaignConverter.entityToResponseDTO(novaCampanha);

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CampaignResponseDTO replaceCampaign(@PathVariable UUID uuid, @RequestBody @Valid CampaignRequestDTO request) {
        Campanha campanha = this.repository.findByUuid(uuid).orElseThrow();
        campanha.setNome(request.getNome());
        campanha.setDescricao(request.getDescricao());
        campanha.setValor(request.getValor());
        Campanha replacedCampaign = this.repository.save(campanha);
        return this.campaignConverter.entityToResponseDTO(replacedCampaign);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@PathVariable UUID uuid) {
        Campanha campanha = this.repository.findByUuid(uuid).orElseThrow();
        this.repository.delete(campanha);
    }




}
