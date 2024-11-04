package com.enoca.enoca.service.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String productName;
    private BigDecimal price;
    private String description;
    private int unitInStock;
    private String sku;
}
