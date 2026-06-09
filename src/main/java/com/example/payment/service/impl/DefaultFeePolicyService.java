package com.example.payment.service.impl;

import org.springframework.stereotype.Service;
import com.example.payment.service.FeePolicyService;

import java.math.BigDecimal;
@Service
public class DefaultFeePolicyService implements FeePolicyService {
    @Override
    public BigDecimal calculateFee(
            BigDecimal amountRub
    ) {
        BigDecimal percent;

        if(amountRub.compareTo(
                BigDecimal.valueOf(1000)
        )<0){
            percent = BigDecimal.valueOf(0.015);
        } else if(amountRub.compareTo(
                BigDecimal.valueOf(5000)
                 )<= 0){
            percent = BigDecimal.valueOf(0.01);
        } else {
            percent = BigDecimal.valueOf(0.005);
        }
        return amountRub.multiply(percent);
    }
}
