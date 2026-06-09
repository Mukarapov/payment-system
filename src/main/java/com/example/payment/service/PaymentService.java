package com.example.payment.service;

import com.example.payment.dto.PaymentCommand;
import com.example.payment.dto.PaymentResult;

public interface PaymentService {
    PaymentResult processPayment(
            PaymentCommand command
    );
}
