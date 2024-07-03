package dev.starryeye.hellospring.subject15_domain_model.payment;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration // 원본은 @Configuration 으로 되어있음
public class TestPaymentConfig {

    /**
     * [7. 스프링 컨테이너와 의존관계 주입]
     * 빈 생성 정보, 런타임 빈 의존관계 정보를 담고 있는 클래스이다.
     */

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new ExchangeProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault()); // Test code 에서는 fixed 로 항상 같은 시간을 리턴하도록하여 테스트하기 편하게 함
    }
}
