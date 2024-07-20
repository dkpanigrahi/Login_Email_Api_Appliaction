package com.email.repository;

import com.email.model.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {
}
