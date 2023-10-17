package com.example.cashsplash.controllers;

import com.example.cashsplash.dtos.product.ProductResponseDTO;
import com.example.cashsplash.services.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {this.productService = productService;}

    @GetMapping("/list-paginated")
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {return productService.getAllProducts(pageable);}

}
