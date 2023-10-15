package com.example.cashsplash.converters;

import com.example.cashsplash.common.Converter;

import com.example.cashsplash.dtos.venda.SaleRequestDto;
import com.example.cashsplash.dtos.venda.SaleResponseDto;
import com.example.cashsplash.models.Sale;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class SaleConverter implements Converter<Sale, SaleRequestDto, SaleResponseDto> {
        private final ModelMapper modelMapper;

        public SaleConverter(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public Sale requestDTOToEntity(SaleRequestDto request) {
            return this.modelMapper.map(request, Sale.class);
        }

        public Sale responseDTOToEntity(SaleResponseDto response) {
            return this.modelMapper.map(response, Sale.class);
        }

        public SaleRequestDto entityToRequestDTO(Sale sale) {
            return this.modelMapper.map(sale, SaleRequestDto.class);
        }

        public SaleResponseDto entityToResponseDTO(Sale sale) {
            return this.modelMapper.map(sale, SaleResponseDto.class);
        }
    }
