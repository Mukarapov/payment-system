package com.example.payment.controller;

import com.example.payment.dto.PaymentCommand;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.dto.PaymentResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.payment.mapper.PaymentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Операции с платежами")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    @Operation(
            summary = "Создать платеж",
            description = "Создает платеж между пользователями"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Платеж успешно создан"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации"
            )
    })
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request
    ) {
        Long payerId = 1L; // временно, для проверки
//                (Long) SecurityContextHolder
//                        .getContext()
//                        .getAuthentication()
//                        .getPrincipal();

        PaymentCommand command =
                paymentMapper.toCommand(
                        request,
                        payerId
                );

        PaymentResult result =
                paymentService.processPayment(
                        command
                );
        return ResponseEntity.ok(
                paymentMapper.toResponse(result)
        );
    }


}
