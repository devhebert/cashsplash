package com.example.cashsplash.repositories;

import com.example.cashsplash.models.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campanha, Long> {

    Optional<Campanha> findByUuid(UUID uuid);
    List<Campanha> findByNomeContainingIgnoreCase(String nome);
}