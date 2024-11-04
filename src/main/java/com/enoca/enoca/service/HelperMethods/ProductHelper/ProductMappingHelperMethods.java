package com.enoca.enoca.service.HelperMethods.ProductHelper;

import com.enoca.enoca.model.Product;
import com.enoca.enoca.service.DTO.Product.GetAllProductResponse;
import com.enoca.enoca.service.DTO.Product.GetByIdProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductMappingHelperMethods {
    public List<GetAllProductResponse> mapToDtoList(List<Product> products) {
        return products.stream().map(
                product -> GetAllProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .unitInStock(product.getUnitInStock())
                        .sku(product.getSku())
                        .build()).collect(Collectors.toList());
    }

    public GetByIdProductResponse mapToDtoById(Product product) {
        return GetByIdProductResponse.builder()
                .id(product.getProductName())
                .productName(product.getProductName())
                .price(product.getPrice())
                .description(product.getDescription())
                .unitInStock(product.getUnitInStock())
                .sku(product.getSku())
                .build();
    }

}
