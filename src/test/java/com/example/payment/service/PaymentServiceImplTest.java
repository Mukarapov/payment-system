package com.example.payment.service;

import com.example.payment.dto.PaymentCommand;
import com.example.payment.dto.PaymentResult;
import com.example.payment.enums.Currency;
import com.example.payment.event.PaymentCreatedEvent;
import com.example.payment.model.Payment;
import com.example.payment.model.User;
import com.example.payment.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private FeePolicyService feePolicyService;

    @Mock
    private PaymentStoreService paymentStoreService;

    @Mock
    private ApplicationEventPublisher events;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldCreatePaymentSuccessfully() {

        User payer = User.builder()
                .id(1L)
                .username("ivan")
                .build();

        User recipient = User.builder()
                .id(2L)
                .username("petr")
                .build();

        PaymentCommand command = PaymentCommand.builder()
                .amount(BigDecimal.valueOf(100))
                .currency(Currency.USD)
                .payerId(1L)
                .recipientId(2L)
                .build();

        when(userService.getById(1L))
                .thenReturn(payer);

        when(userService.getById(2L))
                .thenReturn(recipient);

        when(exchangeRateService.rateForToday(
                "USD",
                "RUB"
        )).thenReturn(BigDecimal.valueOf(90));

        when(feePolicyService.calculateFee(any()))
                .thenReturn(BigDecimal.valueOf(45));

        Payment savedPayment = Payment.builder()
                .id(1L)
                .build();

        when(paymentStoreService.savePayment(any()))
                .thenReturn(savedPayment);

        PaymentResult result =
                paymentService.processPayment(command);

        assertEquals(1L, result.getPaymentId());
        assertEquals(
                BigDecimal.valueOf(45),
                result.getFee()
        );

        verify(paymentStoreService)
                .savePayment(any());

        verify(paymentStoreService)
                .saveFee(any());

        verify(events)
                .publishEvent(any(PaymentCreatedEvent.class));
    }
    @Test
    void shouldThrowExceptionWhenTransferToYourself() {

        User user = User.builder()
                .id(1L)
                .username("ivan")
                .build();

        PaymentCommand command =
                PaymentCommand.builder()
                        .amount(BigDecimal.valueOf(100))
                        .currency(Currency.USD)
                        .payerId(1L)
                        .recipientId(1L)
                        .build();

        when(userService.getById(1L))
                .thenReturn(user);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> paymentService.processPayment(command)
                );

        assertEquals(
                "Cannot transfer money to yourself",
                exception.getMessage()
        );

        verify(paymentStoreService, never())
                .savePayment(any());


    }

}
