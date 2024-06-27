package dev.starryeye.hellospring;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
public class Payment {

    private final Long orderId;
    private final String currency;
    private final BigDecimal foreignCurrencyAmount; // 외국 돈으로 얼마인가
    private final BigDecimal exchangeRate; // 환율
    private final BigDecimal convertedAmount; // 원화로 얼마인가
    private final LocalDateTime validUntil; // 유효시간

    // 소수점 계산 시, 특히 돈 계산을 할 때 Double, Float 자료형을 사용하면 금액 계산에 오차가 발생하기 쉬우므로 BigDecimal 을 사용하자

    @Builder
    private Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exchangeRate = exchangeRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }
}
