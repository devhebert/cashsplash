package com.example.cashsplash.services.product;
import com.example.cashsplash.converters.ProductConverter;
import com.example.cashsplash.dtos.product.ProductRequestDTO;
import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.models.Product;
import com.example.cashsplash.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productConverter::entityToResponseDTO);
    }

    public Page<ProductResponseDTO> getProductForUuid(Pageable pageable, UUID uuid) {
        return productRepository.findByUuid(pageable, uuid).map(productConverter::entityToResponseDTO);
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productConverter.requestDTOToEntity(productRequestDTO);
        product.setUuid(UUID.randomUUID());
        Product newProduct = productRepository.save(product);
        return productConverter.entityToResponseDTO(newProduct);
    }
}
