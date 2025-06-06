package com.petfood.userservice.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token; // Agora representa o userId
}