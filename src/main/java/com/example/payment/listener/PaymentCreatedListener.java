package com.example.payment.listener;

import com.example.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import com.example.payment.service.NotificationService;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCreatedListener {
    private final NotificationService notificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PaymentCreatedEvent event){

        try {
            notificationService.notifyPaymentCreated(
                    event.paymentId()
            );
        }catch (Exception e){
            log.error(
                    "Notification failed for payment {}",
                    event.paymentId(),
                    e
            );
        }
    }
}
