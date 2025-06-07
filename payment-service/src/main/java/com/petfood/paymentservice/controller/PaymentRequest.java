package com.petfood.paymentservice.controller;

import lombok.Data;

@Data
public class PaymentRequest {
    private String orderId;
    private String userId;
    private double amount;
}