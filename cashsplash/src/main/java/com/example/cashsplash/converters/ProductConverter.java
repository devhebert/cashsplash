package com.example.cashsplash.converters;

import com.example.cashsplash.common.Converter;
import com.example.cashsplash.dtos.product.ProductRequestDTO;
import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductRequestDTO, ProductResponseDTO> {
    private final ModelMapper modelMapper;

    public ProductConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Product requestDTOToEntity(ProductRequestDTO request) {
        return this.modelMapper.map(request, Product.class);
    }

    public Product responseDTOToEntity(ProductResponseDTO response) {
        return this.modelMapper.map(response, Product.class);
    }

    public ProductRequestDTO entityToRequestDTO(Product customer) {
        return this.modelMapper.map(customer, ProductRequestDTO.class);
    }

    public ProductResponseDTO entityToResponseDTO(Product customer) {
        return this.modelMapper.map(customer, ProductResponseDTO.class);
    }
}

