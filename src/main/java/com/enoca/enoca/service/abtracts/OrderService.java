package com.enoca.enoca.service.abtracts;

import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.Order;
import com.enoca.enoca.model.OrderItem;

import java.util.List;

public interface OrderService {
    Order placeOrder(String customerId);
    Order getOrderForCode(String orderId);
    List<Order> getAllOrdersForCustomer(String customerId);
}
