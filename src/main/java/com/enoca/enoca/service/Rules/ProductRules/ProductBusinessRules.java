package com.enoca.enoca.service.Rules.ProductRules;

import com.enoca.enoca.core.exceptions.ProductSkuAlreadyExists;
import com.enoca.enoca.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductBusinessRules {
    private final ProductRepository productRepository;
    public ProductBusinessRules(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void checkIfProductSkuExists(String sku) {
        if (this.productRepository.existsBysku(sku)) {
            throw new ProductSkuAlreadyExists("Product sku already exists. ");

        }
    }
}
