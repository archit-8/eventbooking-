package com.archit.eventbooking.controller;

import com.archit.eventbooking.dto.*;
import com.archit.eventbooking.entity.User;
import com.archit.eventbooking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid  @RequestBody UserRegistrationRequest request) {

        UserResponse user = userService.register(request);

        ApiResponse<UserResponse> response =
                new ApiResponse<>(true, "User registered successfully", user);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login( @Valid @RequestBody LoginRequest request) {

        AuthResponse authResponse = userService.login(request);

        ApiResponse<AuthResponse> response =
                new ApiResponse<>(true, "Login successful", authResponse);

        return ResponseEntity.ok(response);
    }
}