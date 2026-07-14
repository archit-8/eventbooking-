package com.archit.eventbooking.service;
import com.archit.eventbooking.config.JwtUtil;
import com.archit.eventbooking.dto.AuthResponse;
import com.archit.eventbooking.dto.LoginRequest;
import com.archit.eventbooking.dto.UserRegistrationRequest;
import com.archit.eventbooking.dto.UserResponse;
import com.archit.eventbooking.entity.Role;
import com.archit.eventbooking.entity.User;
import com.archit.eventbooking.exception.InvalidCredentialsException;
import com.archit.eventbooking.exception.UserAlreadyExistsException;
import com.archit.eventbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);
    @Transactional
    public UserResponse register(UserRegistrationRequest request) {

        logger.info("Register request received for email: {}", request.getEmail());
        if (request.getEmail() == null || request.getPassword() == null) {
            logger.warn("Invalid registration request: email/password missing");
            throw new IllegalArgumentException("Email and password must not be null");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            logger.warn("User already exist with email:{}",request.getEmail());
            throw new UserAlreadyExistsException(
                    "User already exists with email: " + request.getEmail()
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
    public AuthResponse login(LoginRequest request) {

        logger.info("logging attempt for  email :{}",request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("login failed:user not found for email: {}",request.getEmail());
                   return new InvalidCredentialsException("Invalid credentials");
                });


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("login failed:incorrect password  for email{}",request.getEmail());
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        logger.info("login is successful for email:{}",request.getEmail());

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }

    public User findByEmail(String email) {
        logger.info("fetching user my email id : {}",email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("user not found with email: {}",email);
                    return new RuntimeException("User not found");
                });
    }

}