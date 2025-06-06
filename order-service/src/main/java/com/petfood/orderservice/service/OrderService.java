package com.petfood.orderservice.service;

import com.petfood.orderservice.model.Order;
import com.petfood.orderservice.model.OrderItem;
import com.petfood.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    @Value("${catalog-service.url}")
    private String catalogServiceUrl;
    @Value("${user-service.url}")
    private String userServiceUrl;

    public Order createOrder(String userId, List<OrderItem> items) {
        // Verificar usuário que tava me travano aaaaaaaaa
        Map<String, Object> user = restTemplate.getForObject(
                userServiceUrl + "/api/users/" + userId, Map.class);
        if (user == null) {
            throw new RuntimeException("User not found: " + userId);
        }

        double totalPrice = 0;
        for (OrderItem item : items) {
            Map<String, Object> product = restTemplate.getForObject(
                    catalogServiceUrl + "/api/products/" + item.getProductId(), Map.class);
            if (product == null) {
                throw new RuntimeException("Product not found: " + item.getProductId());
            }
            double price = ((Number) product.get("price")).doubleValue();
            String name = (String) product.get("name");
            item.setUnitPrice(price);
            item.setProductName(name);
            totalPrice += price * item.getQuantity();
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(items);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public void updateStatus(String id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
}