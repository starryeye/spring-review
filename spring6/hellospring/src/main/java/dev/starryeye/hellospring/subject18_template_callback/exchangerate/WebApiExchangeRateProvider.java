package dev.starryeye.hellospring.subject18_template_callback.exchangerate;

import dev.starryeye.hellospring.subject18_template_callback.api.*;
import dev.starryeye.hellospring.subject18_template_callback.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    /**
     * template callback pattern 은 전략 패턴이라 볼 수 있다.
     */

    private final ApiTemplate apiTemplate = new ApiTemplate(); // template

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {
        /**
         * Client
         *
         * 클라이언트가 콜백을 만들어서 템플릿을 호출한다.
         */

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        /**
         * new SimpleApiExecutor, new ErApiExchangeRateExtractor
         * new HttpClientApiExecutor
         * -> callback
         *
         * callback 은 익명 내부 클래스, 람다로도 가능하다.
         */
        return apiTemplate.getExchangeRate(quoteCurrency, url, new HttpClientApiExecutor(), new ErApiExchangeRateExtractor());
    }
}
