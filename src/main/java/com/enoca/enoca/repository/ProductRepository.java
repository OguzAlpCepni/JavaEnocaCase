package com.enoca.enoca.repository;

import com.enoca.enoca.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

    boolean existsBysku(String sku);
}
