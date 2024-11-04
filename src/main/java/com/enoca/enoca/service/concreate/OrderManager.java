package com.enoca.enoca.service.concreate;

import com.enoca.enoca.core.exceptions.CustomerNotFoundException;
import com.enoca.enoca.model.*;
import com.enoca.enoca.repository.CustomerRepository;
import com.enoca.enoca.repository.HistoricalProductPriceRepository;
import com.enoca.enoca.repository.OrderRepository;
import com.enoca.enoca.repository.ProductRepository;
import com.enoca.enoca.service.abtracts.CardService;
import com.enoca.enoca.service.abtracts.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CardService cardService;
    private final HistoricalProductPriceRepository historicalProductPriceRepository;
    private final CustomerRepository customerRepository;



    public OrderManager(OrderRepository orderRepository, ProductRepository productRepository,CustomerRepository customerRepository,HistoricalProductPriceRepository historicalProductPriceRepository, CardService cardService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cardService = cardService;
        this.historicalProductPriceRepository = historicalProductPriceRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public Order placeOrder(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        Card card = customer.getCard();
        // Yeni bir sipariş oluşturun
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(card.getTotalPrice());
        order.setOrderItems(new ArrayList<>());

        // Sepetteki her ürünü siparişe ekleyin
        for (CardItem cardItem : card.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cardItem.getProduct()); // Ürün nesnesini ayarlayın
            orderItem.setQuantity(cardItem.getQuantity());
            orderItem.setPriceAtOrderTime(cardItem.getProduct().getPrice()); // Mevcut fiyat kaydediliyor
            orderItem.setTotalPrice(cardItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cardItem.getQuantity())));
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            // Ürünün stokunu güncelle
            Product product = cardItem.getProduct();
            int newStock = product.getUnitInStock() - cardItem.getQuantity();
            // HistoricalPrice kaydı ekleme işlemi
            // Stok miktarı negatif olmamalı

            if (newStock < 0) {
                throw new IllegalArgumentException("Yetersiz stok: " + product.getProductName());
            }
            product.setUnitInStock(newStock);
            productRepository.save(product);


            HistoricalProductPrices historicalPrice = new HistoricalProductPrices();
            historicalPrice.setPrice(cardItem.getProduct().getPrice());
            historicalPrice.setQuantity(cardItem.getQuantity());
            historicalPrice.setEffectiveDate(LocalDateTime.now()); // Geçerli tarih
            historicalProductPriceRepository.save(historicalPrice);
            historicalProductPriceRepository.save(historicalPrice);
        }

        // Siparişi kaydedin
        orderRepository.save(order);

        // Sepeti boşaltın
        cardService.emptyCart(customerId);

        return order;
    }
    public Order getOrderForCode(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("don't foundy any order"));
    }

    public List<Order> getAllOrdersForCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


}