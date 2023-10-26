package com.example.cashsplash.services.sale;

import com.example.cashsplash.converters.SaleConverter;
import com.example.cashsplash.dtos.ItemDto;
import com.example.cashsplash.dtos.sale.SaleRequestDto;
import com.example.cashsplash.dtos.sale.SaleResponseDto;
import com.example.cashsplash.models.*;
import com.example.cashsplash.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleConverter saleConverter;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;


    public Page<SaleResponseDto> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable).map(saleConverter::entityToResponseDTO);
    }

    public Page<SaleResponseDto> getSalesForUuid(Pageable pageable, UUID uuid) {
        return saleRepository.findByUuid(pageable, uuid).map(saleConverter::entityToResponseDTO);
    }


    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        Long userId = saleRequestDto.getIdUser();
        Long customerId = saleRequestDto.getIdCustomer();
        List<ItemDto> itemsDto = saleRequestDto.getItems();
        Long campaignId = saleRequestDto.getIdCampaign();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Sale sale = new Sale();
        if(itemsDto != null) {
            itemsDto.forEach(itemDto -> {
                var product = productRepository.findById(itemDto.getIdProduct())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
                SaleItem saleItem = new SaleItem(null, sale, product, itemDto.getAmount());
                sale.getItems().add(saleItem);
            });
        }

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));

        sale.setUuid(UUID.randomUUID());
        sale.setUser(user);
        sale.setCustomer(customer);
        sale.setCampaign(campaign);

        Sale newSale = saleRepository.save(sale);

        return saleConverter.entityToResponseDTO(newSale);
    }

    public void deleteSale(UUID uuid) {
        Sale sale = saleRepository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Sale not found"));
        saleRepository.delete(sale);
    }
}