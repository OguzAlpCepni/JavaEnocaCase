package com.enoca.enoca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historical_product_prices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoricalProductPrices extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal price;
    private LocalDateTime effectiveDate;
    private int quantity;
    // Constructor, Getters, and Setters
}