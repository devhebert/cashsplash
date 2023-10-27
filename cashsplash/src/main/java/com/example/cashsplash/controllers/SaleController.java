package com.example.cashsplash.controllers;

import com.example.cashsplash.dtos.sale.SaleCalculationResultDto;
import com.example.cashsplash.dtos.sale.SaleRequestDto;
import com.example.cashsplash.dtos.sale.SaleResponseDto;
import com.example.cashsplash.services.sale.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/list-paginated")
    public Page<SaleResponseDto> getAllSales(Pageable pageable) {
        return saleService.getAllSales(pageable);
    }

    @GetMapping(params = {"uuid"})
    public Page<SaleResponseDto> getSalesForUuid(Pageable pageable, @RequestParam UUID uuid) {
        return saleService.getSalesForUuid(pageable, uuid);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponseDto createSale(@Valid @RequestBody SaleRequestDto saleRequestDto) {
        return saleService.createSale(saleRequestDto);
    }

    @GetMapping("/{uuid}/calculate-totals")
    public SaleCalculationResultDto calculateSaleTotals(@PathVariable UUID uuid) {
        return saleService.calculateSaleTotals(uuid);
    }

    @DeleteMapping("/delete/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable UUID uuid) {
        saleService.deleteSale(uuid);
    }
}
