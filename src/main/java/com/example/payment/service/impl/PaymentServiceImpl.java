package com.example.payment.service.impl;

import com.example.payment.dto.PaymentCommand;
import com.example.payment.dto.PaymentResult;
import com.example.payment.event.PaymentCreatedEvent;
import com.example.payment.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.payment.model.Fee;
import com.example.payment.model.Payment;
import com.example.payment.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional

public class PaymentServiceImpl implements PaymentService {

    private final UserService userService;
    private final ExchangeRateService exchangeRateService;
    private final FeePolicyService feePolicyService;
    private final PaymentStoreService paymentStoreService;
    private final ApplicationEventPublisher events;

    @Override
    public PaymentResult processPayment(
            PaymentCommand command
    ) {
        User payer =
                userService.getById(command.getPayerId()
                );
        User recipient = userService.getById(
                command.getRecipientId()
        );
        if (payer.getId().equals(recipient.getId())){
            throw new IllegalArgumentException(
                    "Cannot transfer money to yourself"
            );
        }
        BigDecimal rate = exchangeRateService.rateForToday(
                command.getCurrency().name(),
                "RUB"
        );

        BigDecimal amountRub =
                command.getAmount().multiply(rate);

        BigDecimal feeValue = feePolicyService.calculateFee(
                amountRub
        );

        Payment payment = Payment.builder()
                .amount(command.getAmount())
                .currency(command.getCurrency())
                .amountRub(amountRub)
                .payer(payer)
                .recipient(recipient)
                .build();

        payment = paymentStoreService.savePayment(
                payment
        );

        Fee fee = Fee.builder()
                .value(feeValue)
                .user(payer)
                .payment(payment)
                .build();

        paymentStoreService.saveFee(fee);

        events.publishEvent(
                new PaymentCreatedEvent(
                        payment.getId()
                )
        );
        return PaymentResult.builder()
                .paymentId(payment.getId())
                .amountRub(amountRub)
                .fee(feeValue)
                .build();
    }
}
