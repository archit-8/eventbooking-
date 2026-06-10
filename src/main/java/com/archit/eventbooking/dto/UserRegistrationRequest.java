package com.archit.eventbooking.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
}