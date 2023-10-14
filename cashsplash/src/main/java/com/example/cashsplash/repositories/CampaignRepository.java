package com.example.cashsplash.repositories;

import com.example.cashsplash.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByUuid(UUID uuid);
    List<Campaign> findByNameContainingIgnoreCase(String nome);
}