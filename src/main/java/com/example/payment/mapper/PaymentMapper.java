package com.example.payment.mapper;

import com.example.payment.dto.PaymentCommand;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.dto.PaymentResult;
import com.example.payment.enums.Currency;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public PaymentCommand toCommand(
            PaymentRequest request,
            Long payerId
    ){

        return PaymentCommand.builder()
                .amount(request.getAmount())
                .currency(Currency.valueOf(request.getCurrencyCode().toUpperCase()))
                .recipientId(request.getRecipientId())
                .payerId(payerId)
                .build();
    }
    public PaymentResponse toResponse(
        PaymentResult result
    ){
        return PaymentResponse.builder()
                .paymentId(result.getPaymentId())
                .amountRub(result.getAmountRub())
                .fee(result.getFee())
                .build();
    }
}
