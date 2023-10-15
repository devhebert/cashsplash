package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.SaleConverter;
import com.example.cashsplash.dtos.venda.SaleRequestDto;
import com.example.cashsplash.dtos.venda.SaleResponseDto;
import com.example.cashsplash.models.Sale;
import com.example.cashsplash.repositories.SaleRepository;
import com.example.cashsplash.services.sale.SaleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/sales")
public class SaleController {

    private final SaleRepository saleRepository;
    private final SaleConverter saleConverter;
    private final SaleService saleService;

    public SaleController(SaleRepository saleRepository, SaleConverter saleConverter, SaleService saleService) {
        this.saleRepository = saleRepository;
        this.saleConverter = saleConverter;
        this.saleService = saleService;
    }

    @GetMapping("/list-paginated")
    public Page<SaleRequestDto> getAllSales(Pageable pageable) {
        return this.saleRepository.findAll(pageable).map(saleConverter::entityToRequestDTO);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponseDto createSale(@Valid @RequestBody SaleRequestDto saleRequestDto) {
        Sale sale = this.saleConverter.requestDTOToEntity(saleRequestDto);
        Sale newSale = this.saleRepository.save(sale);
        return this.saleConverter.entityToResponseDTO(newSale);
    }

    @DeleteMapping("/delete/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        Sale sale = this.saleRepository.findByUuid(uuid).orElseThrow();
        this.saleRepository.delete(sale);
    }
}
