package dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExchangeRateExtractor exchangeRateExtractor;

    public ApiTemplate() {
        /**
         * template 이 default callback 을 가지고 있도록 한다.
         */
        this.apiExecutor = new HttpClientApiExecutor();
        this.exchangeRateExtractor = new ErApiExchangeRateExtractor();
    }

    public ApiTemplate(ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
        /**
         * template 의 default callback 을 template 이 생성될 때 외부에서 주입되도록 한다.
         */
        this.apiExecutor = apiExecutor;
        this.exchangeRateExtractor = exchangeRateExtractor;
    }

    // 편의 메서드
    public BigDecimal getExchangeRate(String quoteCurrency, String url) {
        /**
         * default callback 을 사용하는 편의 메서드이다.
         */
        return getExchangeRate(quoteCurrency, url, this.apiExecutor, this.exchangeRateExtractor);
    }

    // 편의 메서드
    public BigDecimal getExchangeRate(String quoteCurrency, String url, ApiExecutor apiExecutor) {
        // 일부는 default, 일부는 직접 callback 주입
        return getExchangeRate(quoteCurrency, url, apiExecutor, this.exchangeRateExtractor);
    }

    // 편의 메서드
    public BigDecimal getExchangeRate(String quoteCurrency, String url, ExchangeRateExtractor exchangeRateExtractor) {
        // 일부는 default, 일부는 직접 callback 주입
        return getExchangeRate(quoteCurrency, url, this.apiExecutor, exchangeRateExtractor);
    }

    public BigDecimal getExchangeRate(String quoteCurrency, String url, ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
        /**
         * template
         *
         * 런타임에 외부에서 주입해준 콜백을 사용한다.
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
