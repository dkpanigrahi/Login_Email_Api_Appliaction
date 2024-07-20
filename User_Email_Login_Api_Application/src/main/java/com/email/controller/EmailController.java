package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.email.model.EmailRequest;
import com.email.service.EmailService;
import com.email.repository.EmailRequestRepository;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRequestRepository emailRequestRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "hello this is my email api";
    }

    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        System.out.println(request);
        boolean result = emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        
        // Save email request to the database
        emailRequestRepository.save(request);

        if (result) {
            return ResponseEntity.ok("Email Sent Successfully.........");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent.....");
        }
    }
}
