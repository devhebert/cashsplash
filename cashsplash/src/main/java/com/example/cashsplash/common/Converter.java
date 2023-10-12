package com.example.cashsplash.common;

public interface Converter<ENTITY, REQUEST_DTO, RESPONSE_DTO> {
    ENTITY requestDTOToEntity(REQUEST_DTO dto);
    ENTITY responseDTOToEntity(RESPONSE_DTO dto);
    REQUEST_DTO entityToRequestDTO(ENTITY entity);
    RESPONSE_DTO entityToResponseDTO(ENTITY entity);
}