package com.enoca.enoca.repository;

import com.enoca.enoca.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomerId(String customerId); // Müşteriye göre siparişleri bul
}
