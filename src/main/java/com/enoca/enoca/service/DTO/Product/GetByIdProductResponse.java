package com.enoca.enoca.service.DTO.Product;

import com.enoca.enoca.model.CardItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductResponse {
    private String id;
    private String productName;
    private BigDecimal price;
    private String description;
    private int unitInStock;
    private String sku;

}
