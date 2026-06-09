package com.example.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SecondaryRow;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на создание платежа")
public class PaymentRequest {
    @Schema(
            description = "Сумма платежа",
            example = "100.50"
    )
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
    @Schema(
            description = "Код валюты",
            example = "USD"
    )
    @NotBlank
    private String currencyCode;
    @Schema(
            description = "Получатель"
    )
    @NotNull
    private Long recipientId;
}
