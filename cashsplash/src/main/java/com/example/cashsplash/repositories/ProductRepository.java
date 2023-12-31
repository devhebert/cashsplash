package com.example.cashsplash.repositories;

import com.example.cashsplash.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByUuid(Pageable pageable, UUID uuid);
    List<Product> findByIdIn(List<Long> ids);
}
