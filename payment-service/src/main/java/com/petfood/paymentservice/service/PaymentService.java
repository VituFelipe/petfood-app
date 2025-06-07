package com.petfood.paymentservice.service;

import com.petfood.paymentservice.model.Payment;
import com.petfood.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
    @Value("${order-service.url}")
    private String orderServiceUrl;

    public Payment processPayment(String orderId, String userId, double amount) {
        Map<String, Object> order = restTemplate.getForObject(
                orderServiceUrl + "/api/orders/" + orderId, Map.class);
        if (order == null) {
            throw new RuntimeException("Order not found: " + orderId);
        }
        if (amount != ((Number) order.get("totalPrice")).doubleValue()) {
            throw new RuntimeException("Invalid amount for order: " + orderId);
        }

        Random random = new Random();
        boolean success = random.nextDouble() < 0.8;

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setStatus(success ? "CONFIRMED" : "FAILED");
        payment.setCreatedAt(LocalDateTime.now());

        restTemplate.postForObject(
                orderServiceUrl + "/api/orders/" + orderId + "/status",
                Map.of("status", success ? "CONFIRMED" : "CANCELLED"),
                Void.class);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}