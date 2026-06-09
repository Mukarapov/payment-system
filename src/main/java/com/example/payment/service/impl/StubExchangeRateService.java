package com.example.payment.service.impl;

import org.springframework.stereotype.Service;
import com.example.payment.service.ExchangeRateService;

import java.math.BigDecimal;

@Service
public class StubExchangeRateService implements ExchangeRateService {

    @Override
    public BigDecimal rateForToday(
            String currencyCode,
            String targetCurrencyCode
    ) {
        if("USD".equalsIgnoreCase(currencyCode)){
            return BigDecimal.valueOf(90);
        }
        if("EUR".equalsIgnoreCase(currencyCode)){
            return BigDecimal.valueOf(100);
        }
        return BigDecimal.ONE;
    }


}
