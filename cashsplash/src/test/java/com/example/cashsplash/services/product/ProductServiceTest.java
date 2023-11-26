package com.example.cashsplash.services.product;

import com.example.cashsplash.converters.ProductConverter;
import com.example.cashsplash.dtos.product.ProductRequestDTO;
import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.models.Product;
import com.example.cashsplash.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProductsShouldReturnProductPage() {
        Pageable pageable = mock(Pageable.class);
        Page<Product> productPage = mock(Page.class);
        when(productRepository.findAll(pageable)).thenReturn(productPage);

        productService.getAllProducts(pageable);

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void getProductForUuidShouldReturnProductPage() {
        Pageable pageable = mock(Pageable.class);
        UUID uuid = UUID.randomUUID();
        Page<Product> productPage = mock(Page.class);
        when(productRepository.findByUuid(pageable, uuid)).thenReturn(productPage);

        productService.getProductForUuid(pageable, uuid);

        verify(productRepository, times(1)).findByUuid(pageable, uuid);
    }

    @Test
    void createProductShouldReturnCreatedProduct() {
        ProductRequestDTO productRequestDTO = mock(ProductRequestDTO.class);
        Product product = mock(Product.class);
        when(productConverter.requestDTOToEntity(productRequestDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        ProductResponseDTO productResponseDTO = mock(ProductResponseDTO.class);
        when(productConverter.entityToResponseDTO(product)).thenReturn(productResponseDTO);

        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);

        assertEquals(productResponseDTO, createdProduct);
        verify(productRepository, times(1)).save(product);
    }
}