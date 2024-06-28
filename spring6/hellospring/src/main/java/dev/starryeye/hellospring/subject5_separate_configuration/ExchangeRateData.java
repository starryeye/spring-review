package dev.starryeye.hellospring.subject5_separate_configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 여기에 정의되지 않은 필드 값이 있으면 무시하고 여기에 정의한 필드만 이 객체로 매핑한다.
public record ExchangeRateData(
        String result,
        Map<String, BigDecimal> rates
) {
}
