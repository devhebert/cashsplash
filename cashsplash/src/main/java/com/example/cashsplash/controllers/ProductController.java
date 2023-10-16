package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.ProductConverter;
import com.example.cashsplash.dtos.product.ProductRequestDTO;
import com.example.cashsplash.dtos.sale.SaleRequestDto;
import com.example.cashsplash.repositories.ProductRepository;
import com.example.cashsplash.services.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public ProductController(ProductRepository productRepository, ProductService productService, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productConverter = productConverter;

    }
    @GetMapping("/list-paginated")
    public Page<ProductRequestDTO> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(productConverter::entityToRequestDTO);
    }

}
