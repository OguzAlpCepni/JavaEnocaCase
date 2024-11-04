package com.enoca.enoca.repository;

import com.enoca.enoca.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, String> {
     Card findByCustomerId(String customerId);
}
