package dev.starryeye.hellospring.subject20_rest_template.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Clock;
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

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate, LocalDateTime now) {

        /**
         * 도메인 모델 패턴을 적용
         * 트랜잭션 스크립트 패턴으로 PaymentService 에 존재하던 비즈니스 로직을
         * 도메인 모델 패턴을 적용하여 Payment 로 옮겼다.
         *
         * 참고
         * 트랜잭션 스크립트 패턴은 중복 코드가 Service 의 여러 메서드로 흩어져 있을 확률이 높다.
         * (ex. 여러 메서드에서 isValid 를 호출하고 시작해야한다고 생각해보자..)
         * 하지만, 도메인 모델 패턴에서는 중복 없이 Payment 가 가지면 되므로 코드가 간결해진다.
         */

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = now.plusMinutes(30);

        return Payment.builder()
                .orderId(orderId)
                .currency(currency)
                .foreignCurrencyAmount(foreignCurrencyAmount)
                .exchangeRate(exchangeRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }
}
