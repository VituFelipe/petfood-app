package com.petfood.userservice.controller;

import com.petfood.userservice.dto.LoginRequest;
import com.petfood.userservice.dto.LoginResponse;
import com.petfood.userservice.dto.RegisterRequest;
import com.petfood.userservice.model.User;
import com.petfood.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return authService.getUserById(id);
    }
}