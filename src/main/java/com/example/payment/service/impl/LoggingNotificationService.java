package com.example.payment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.payment.service.NotificationService;
@Service
@Slf4j
public class LoggingNotificationService implements NotificationService {
    @Override
    public void notifyPaymentCreated(Long paymentId) {
        log.info("Notification sent for payment {}",
                paymentId);
    }
}
