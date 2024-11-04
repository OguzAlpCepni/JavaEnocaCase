package com.enoca.enoca.service.abtracts;

import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.Product;
import com.enoca.enoca.service.DTO.Product.ProductDto;

public interface CardService {


    public Card getCart(String customerId);
    public void emptyCart(String customerId);
    public void updateCart(String customerId, ProductDto productDto, int quantity);
    public void addProductToCart(String customerId, ProductDto productDto, int quantity);
    public void removeProductFromCart(String customerId, ProductDto productDto);
}
