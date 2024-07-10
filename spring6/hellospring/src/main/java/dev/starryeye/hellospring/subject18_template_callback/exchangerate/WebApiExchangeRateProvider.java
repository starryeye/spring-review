package dev.starryeye.hellospring.subject18_template_callback.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.starryeye.hellospring.subject18_template_callback.api.ApiExecutor;
import dev.starryeye.hellospring.subject18_template_callback.api.ErApiExchangeRateExtractor;
import dev.starryeye.hellospring.subject18_template_callback.api.ExchangeRateExtractor;
import dev.starryeye.hellospring.subject18_template_callback.api.SimpleApiExecutor;
import dev.starryeye.hellospring.subject18_template_callback.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {
        /**
         * Client
         *
         * 클라이언트가 콜백을 만들어서 템플릿을 호출한다.
         */

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        /**
         * new SimpleApiExecutor
         * -> callback
         *
         * callback 은 익명 내부 클래스, 람다로도 가능하다.
         */
        return runApiForExchangeRate(quoteCurrency, url, new SimpleApiExecutor(), new ErApiExchangeRateExtractor());
    }

    private static BigDecimal runApiForExchangeRate(String quoteCurrency, String url, ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
        /**
         * template
         */

        // 1. API 호출을 위한 사전 작업
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // 2. API 호출
        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3. API 호출 응답에서 원하는 값 추출
        try {
            return exchangeRateExtractor.extract(quoteCurrency, response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
