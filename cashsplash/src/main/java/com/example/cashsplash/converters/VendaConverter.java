package com.example.cashsplash.converters;

import com.example.cashsplash.common.Converter;

import com.example.cashsplash.dtos.venda.VendaRequestDto;
import com.example.cashsplash.dtos.venda.VendaResponseDto;
import com.example.cashsplash.models.Venda;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class VendaConverter implements Converter<Venda, VendaRequestDto, VendaResponseDto> {
        private final ModelMapper modelMapper;

        public VendaConverter(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public Venda requestDTOToEntity(VendaRequestDto request) {
            return this.modelMapper.map(request, Venda.class);
        }

        public Venda responseDTOToEntity(VendaResponseDto response) {
            return this.modelMapper.map(response, Venda.class);
        }
        public VendaRequestDto entityToRequestDTO(Venda venda) {
            return this.modelMapper.map(venda, VendaRequestDto.class);
        }
        public VendaResponseDto entityToResponseDTO(Venda venda) {
            return this.modelMapper.map(venda, VendaResponseDto.class);
        }
    }
