package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.VendaConverter;
import com.example.cashsplash.dtos.venda.VendaRequestDto;
import com.example.cashsplash.dtos.venda.VendaResponseDto;
import com.example.cashsplash.models.Venda;
import com.example.cashsplash.repositories.VendaRepository;
import com.example.cashsplash.services.venda.VendaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/vendas")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final VendaConverter vendaConverter;
    private final VendaService vendaService;

    public VendaController(VendaRepository vendaRepository, VendaConverter vendaConverter, VendaService vendaService) {
        this.vendaRepository = vendaRepository;
        this.vendaConverter = vendaConverter;
        this.vendaService = vendaService;
    }

    @GetMapping("listar-paginado")
    public Page<VendaRequestDto> listarTodasVendas(Pageable pageable) {
        return this.vendaRepository.findAll(pageable).map(vendaConverter::entityToRequestDTO);
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponseDto cadastrar(@Valid @RequestBody VendaRequestDto vendaRequestDto) {
        Venda venda = this.vendaConverter.requestDTOToEntity(vendaRequestDto);
        Venda novaVenda = this.vendaRepository.save(venda);
        return this.vendaConverter.entityToResponseDTO(novaVenda);
    }

    @DeleteMapping("excluir/{uuid}")
    public void excluir(@PathVariable UUID uuid) {
        Venda venda = this.vendaRepository.findByUuid(uuid).orElseThrow();
        this.vendaRepository.delete(venda);
    }
}
