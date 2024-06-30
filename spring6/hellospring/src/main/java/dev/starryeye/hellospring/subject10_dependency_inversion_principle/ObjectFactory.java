package dev.starryeye.hellospring.subject10_dependency_inversion_principle;

import dev.starryeye.hellospring.subject10_dependency_inversion_principle.exchangerate.CachedExchangeRateProvider;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.ExchangeRateProvider;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.exchangerate.WebApiExchangeRateProvider;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.PaymentService;
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
    public ExchangeRateProvider cachedExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider());
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExchangeRateProvider());
    }
}
