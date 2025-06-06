package com.petfood.orderservice.controller;

import com.petfood.orderservice.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private List<OrderItem> items;
}