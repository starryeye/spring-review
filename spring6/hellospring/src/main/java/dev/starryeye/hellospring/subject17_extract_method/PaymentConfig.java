package dev.starryeye.hellospring.subject17_extract_method;

import dev.starryeye.hellospring.subject17_extract_method.exchangerate.WebApiExchangeRateProvider;
import dev.starryeye.hellospring.subject17_extract_method.payment.ExchangeRateProvider;
import dev.starryeye.hellospring.subject17_extract_method.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    /**
     * [7. 스프링 컨테이너와 의존관계 주입]
     * 빈 생성 정보, 런타임 빈 의존관계 정보를 담고 있는 클래스이다.
     */

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
