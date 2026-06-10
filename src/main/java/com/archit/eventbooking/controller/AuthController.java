package com.archit.eventbooking.controller;

import com.archit.eventbooking.dto.LoginRequest;
import com.archit.eventbooking.dto.UserRegistrationRequest;
import com.archit.eventbooking.entity.User;
import com.archit.eventbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationRequest request) {
        return userService.register(request);
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}