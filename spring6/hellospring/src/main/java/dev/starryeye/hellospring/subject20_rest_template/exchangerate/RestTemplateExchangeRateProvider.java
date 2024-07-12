package dev.starryeye.hellospring.subject20_rest_template.exchangerate;

import dev.starryeye.hellospring.subject20_rest_template.payment.ExchangeRateProvider;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class RestTemplateExchangeRateProvider implements ExchangeRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExchangeRateProvider(RestTemplate restTemplate) {
        /**
         * RestTemplateExchangeRateProvider 은 Web Api 호출 기술을 이용하는 client 이다.
         *
         * 템플릿 콜백 패턴의 Template 을 담당하는 RestTemplate 을 이용 할 것이다.
         * RestTemplate 내부에는 콜백을 이용하여 작업을 실행한다.
         * 콜백은 우리가 직접 주입해줄 수 도.. default callback 을 이용해볼 수 도 있다.
         * 템플릿은 여러 오브젝트에서 이용할 확률이 높기 때문에 스프링 컨테이너에 싱글톤 빈으로 등록하여 사용하도록한다.
         *
         * 참고
         * RestTemplate 의 콜백은 2 가지가 존재한다.
         * 1. "어떤 Http client 기술"을 사용할 것인가. (default callback 은 SimpleApiExecutor 에 사용된 기술임)
         * 2. 응답 데이터 바디를 "어떤 HttpMessageConverter 기술"을 사용하여 객체로 바인딩 할 것인가.
         */
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        return restTemplate.getForObject(url, ExchangeRateData.class)
                .rates().get(quoteCurrency);
    }
}
