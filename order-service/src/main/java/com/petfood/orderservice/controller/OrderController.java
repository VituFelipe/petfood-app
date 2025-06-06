package com.petfood.orderservice.controller;

import com.petfood.orderservice.model.Order;
import com.petfood.orderservice.model.OrderItem;
import com.petfood.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request.getUserId(), request.getItems());
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam String userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable String id, @RequestBody Map<String, String> status) {
        orderService.updateStatus(id, status.get("status"));
    }
}