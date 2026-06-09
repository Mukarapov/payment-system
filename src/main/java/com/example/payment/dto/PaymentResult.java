package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResult {
    private Long paymentId;

    private BigDecimal amountRub;

    private BigDecimal fee;
}
