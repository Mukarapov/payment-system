package com.example.payment.service;

import com.example.payment.model.Fee;
import com.example.payment.model.Payment;

public interface PaymentStoreService {
    Payment savePayment(
            Payment payment
    );

    Fee saveFee(
            Fee fee
    );
}
