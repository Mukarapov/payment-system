package com.example.payment.service;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal rateForToday(
            String currencyCode,
            String targetCurrencyCode
    );
}
