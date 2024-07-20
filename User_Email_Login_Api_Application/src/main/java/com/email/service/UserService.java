package com.email.service;

import com.email.model.User;
import com.email.model.EmailRequest;
import com.email.repository.UserRepository;
import com.email.repository.EmailRequestRepository;
import com.email.model.Otp;
import com.email.model.UserSession;
import com.email.repository.OtpRepository;
import com.email.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRequestRepository emailRequestRepository;

    public boolean registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean generateAndSendOtp(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String otp = generateOtp();
            Otp otpEntity = new Otp(email, otp);
            otpRepository.save(otpEntity);
            EmailRequest emailRequest = new EmailRequest(email, "Your OTP", "Your OTP is: " + otp, otp);
            emailRequestRepository.save(emailRequest);
            emailService.sendEmail("Your OTP", "Your OTP is: " + otp, email);
            return true;
        }
        return false;
    }

    public String verifyOtp(String email, String otp) {
        Optional<Otp> otpEntity = otpRepository.findByEmail(email);
        if (otpEntity.isPresent() && otpEntity.get().getOtp().equals(otp)) {
            otpRepository.delete(otpEntity.get());
            String token = generateSessionToken(email);
            return token;
        }
        return null;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private String generateSessionToken(String email) {
        String token = UUID.randomUUID().toString();
        UserSession userSession = new UserSession(email, token);
        userSessionRepository.save(userSession);
        return token;
    }
}
