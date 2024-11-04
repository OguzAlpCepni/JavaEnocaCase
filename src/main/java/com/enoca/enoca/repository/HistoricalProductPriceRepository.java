package com.enoca.enoca.repository;

import com.enoca.enoca.model.HistoricalProductPrices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoricalProductPriceRepository extends JpaRepository<HistoricalProductPrices,String> {

    Optional<HistoricalProductPrices> findTopByProductIdOrderByEffectiveDateDesc(String productId);
}
