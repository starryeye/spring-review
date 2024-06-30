package dev.starryeye.hellospring.subject7_spring_container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

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
        return new PaymentService(exchangeRateProvider());
    }
}
