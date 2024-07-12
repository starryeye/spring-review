package dev.starryeye.hellospring.subject20_rest_template;

import dev.starryeye.hellospring.subject20_rest_template.exchangerate.RestTemplateExchangeRateProvider;
import dev.starryeye.hellospring.subject20_rest_template.payment.ExchangeRateProvider;
import dev.starryeye.hellospring.subject20_rest_template.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {


    @Bean
    public RestTemplate restTemplate() {
        /**
         * RestTemplate 의 두가지 콜백 중..
         * 어떤 Http Client 기술을 사용할 것인가에 대한 콜백은
         * 아래와 같이 RestTemplate 의 생성자에 넣어주면된다.
         * JdkClientHttpRequestFactory 는 Java 11 에 추가된 Http client 기술 이다. (HttpClientApiExecutor 에 사용된 기술임)
         */
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {

        /**
         * PaymentService -> WebApiExchangeRateProvider -> ApiTemplate
         * 에서..
         * PaymentService -> RestTemplateExchangeRateProvider -> RestTemplate
         * 으로..
         *
         * 구성이 변경 되었다.
         */

        return new RestTemplateExchangeRateProvider(restTemplate());
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
