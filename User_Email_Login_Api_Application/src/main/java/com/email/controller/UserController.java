package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.email.model.EmailRequest;
import com.email.model.User;
import com.email.service.EmailService;
import com.email.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("Registration successful. Please verify your email.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered.");
        }
    }

    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody EmailRequest request) {
        boolean result = userService.generateAndSendOtp(request.getTo());
        if (result) {
            return ResponseEntity.ok("OTP sent to your email.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending OTP.");
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody EmailRequest request) {
        String token = userService.verifyOtp(request.getTo(), request.getOtp());
        if (token != null) {
            return ResponseEntity.ok().body("Login successful. Token: " + token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP.");
        }
    }
}
