package com.example.cashsplash.controllers;

import com.example.cashsplash.dtos.product.ProductRequestDTO;
import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list-paginated")
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {return productService.getAllProducts(pageable);}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }

}
