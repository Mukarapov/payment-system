package com.example.payment.dto;

import com.example.payment.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PaymentCommand {
    private BigDecimal amount;
    private Currency currency;
    private Long recipientId;
    private Long payerId;
}
