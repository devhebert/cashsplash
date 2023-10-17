package com.example.cashsplash.services.sale;

import com.example.cashsplash.converters.SaleConverter;
import com.example.cashsplash.dtos.sale.SaleRequestDto;
import com.example.cashsplash.dtos.sale.SaleResponseDto;
import com.example.cashsplash.models.Sale;
import com.example.cashsplash.repositories.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleConverter saleConverter;

    public SaleService(SaleRepository saleRepository, SaleConverter saleConverter) {
        this.saleRepository = saleRepository;
        this.saleConverter = saleConverter;
    }

    public Page<SaleResponseDto> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable).map(saleConverter::entityToResponseDTO);
    }

    public Page<SaleResponseDto> getSalesForUuid(Pageable pageable, UUID uuid) {
        return saleRepository.findByUuid(pageable, uuid).map(saleConverter::entityToResponseDTO);
    }

    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        Sale sale = saleConverter.requestDTOToEntity(saleRequestDto);
        sale.setUuid(UUID.randomUUID());
        Sale newSale = saleRepository.save(sale);
        return saleConverter.entityToResponseDTO(newSale);
    }

    public void deleteSale(UUID uuid) {
        Sale sale = saleRepository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Sale not found"));
        saleRepository.delete(sale);
    }
}