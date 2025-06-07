package com.petfood.paymentservice.controller;

import com.petfood.paymentservice.model.Payment;
import com.petfood.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(
                request.getOrderId(), request.getUserId(), request.getAmount());
    }

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }
}