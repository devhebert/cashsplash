package com.example.cashsplash.repositories;

import com.example.cashsplash.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    Optional<Venda> findByUuid(UUID uuid);
}
