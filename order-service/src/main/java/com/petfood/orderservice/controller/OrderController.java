package com.petfood.orderservice.controller;

import com.petfood.orderservice.model.Order;
import com.petfood.orderservice.model.OrderItem;
import com.petfood.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody List<OrderItem> items, Authentication authentication) {
        String userId = authentication.getName();
        return orderService.createOrder(userId, items);
    }

    @GetMapping
    public List<Order> getOrders(Authentication authentication) {
        String userId = authentication.getName();
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}