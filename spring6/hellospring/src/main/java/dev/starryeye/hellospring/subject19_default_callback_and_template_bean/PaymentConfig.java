package dev.starryeye.hellospring.subject19_default_callback_and_template_bean;

import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api.ApiTemplate;
import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api.ErApiExchangeRateExtractor;
import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api.HttpClientApiExecutor;
import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.exchangerate.WebApiExchangeRateProvider;
import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.payment.ExchangeRateProvider;
import dev.starryeye.hellospring.subject19_default_callback_and_template_bean.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public ApiTemplate apiTemplate() {
        /**
         * template bean..
         * 여러 오브젝트에서 사용될 확률이 높은 template 은 빈으로 등록하여 사용하자.
         */
        return new ApiTemplate(new HttpClientApiExecutor(), new ErApiExchangeRateExtractor());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider(apiTemplate());
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
