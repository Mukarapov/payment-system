package com.example.payment.service.impl;


import lombok.RequiredArgsConstructor;
import com.example.payment.model.Fee;
import com.example.payment.model.Payment;
import org.springframework.stereotype.Service;
import com.example.payment.repository.FeeRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.service.PaymentStoreService;

@Service
@RequiredArgsConstructor
public class PaymentStoreServiceImpl implements PaymentStoreService {

    private final PaymentRepository paymentRepository;
    private final FeeRepository feeRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Fee saveFee(Fee fee) {
        return feeRepository.save(fee);
    }
}
