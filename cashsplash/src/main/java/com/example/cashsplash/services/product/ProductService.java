package com.example.cashsplash.services.product;
import com.example.cashsplash.converters.ProductConverter;
import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productConverter::entityToResponseDTO);
    }
}
