package com.enoca.enoca.controller.orderController;

import com.enoca.enoca.model.Order;
import com.enoca.enoca.model.OrderItem;
import com.enoca.enoca.service.abtracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/placeorder/id/{customerId}")
    public ResponseEntity<Order> placeOrder(@PathVariable String customerId) {
        Order order = orderService.placeOrder(customerId);
        return ResponseEntity.ok(order);
    }
    @GetMapping("/id/{orderId}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable String orderId) {
        Order order = orderService.getOrderForCode(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable String customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }


}
