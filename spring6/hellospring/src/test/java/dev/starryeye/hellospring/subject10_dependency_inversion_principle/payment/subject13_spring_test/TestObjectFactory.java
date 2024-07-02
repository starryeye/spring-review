package dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.subject13_spring_test;

import dev.starryeye.hellospring.subject10_dependency_inversion_principle.exchangerate.CachedExchangeRateProvider;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.ExchangeRateProvider;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.PaymentService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@TestConfiguration // 원본은 @Configuration 으로 되어있음
public class TestObjectFactory {

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
        return new PaymentService(exchangeRateProvider());
    }
}
