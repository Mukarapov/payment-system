package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private BigDecimal amountRub;
    private Long fee;
}
