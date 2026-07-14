package com.archit.eventbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message="Name is Required")
    private String name;
    @Email(message="Invalid Email format")
    @NotBlank(message = "Email is Required")
    private String email;
    @Size(min=6, message = "Password should  be at least 6 characters ")
    @NotBlank(message = "Password is Required")
    private String password;
}