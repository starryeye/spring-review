package dev.starryeye.hellospring.subject20_rest_template.exchangerate;

import dev.starryeye.hellospring.subject20_rest_template.api.ApiTemplate;
import dev.starryeye.hellospring.subject20_rest_template.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    private final ApiTemplate apiTemplate; // template

    public WebApiExchangeRateProvider(ApiTemplate apiTemplate) {
        /**
         * template bean..
         *
         * template 은 여러 오브젝트에서 사용될 확률이 높으므로
         * application 레벨에서 한번만 생성하여 공용으로 사용되도록한다.
         * thread-safe 하므로 싱글톤 가능
         * -> 스프링 컨테이너의 싱글톤 빈으로 전환하였다.
         */
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {
        /**
         * Client
         *
         * 클라이언트에서 template 에 callback 을 주입해주지 않아서 템플릿은 default callback 으로 동작한다.
         */

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        return apiTemplate.getExchangeRate(quoteCurrency, url);
    }
}
