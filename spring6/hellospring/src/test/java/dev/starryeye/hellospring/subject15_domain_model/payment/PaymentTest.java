package dev.starryeye.hellospring.subject15_domain_model.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    /**
     * 도메인 모델 패턴을 따르면
     * 도메인 모델 오브젝트를 바로 테스트 해보는게 용이해진다.
     * 순수 자바 Test 로 수행해볼 수 있다.
     */

    @Test
    void createPrepared() {

        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(
                1L,
                "USD",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(clock)
        );

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {

        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault()); // 고정된 시계

        Payment payment = Payment.createPrepared(
                1L,
                "USD",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(clock)
        );

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.ofMinutes(30L)))).isFalse(); // 고정된 시계를 강제로 30분 뒤로 돌린 시간
    }
}
