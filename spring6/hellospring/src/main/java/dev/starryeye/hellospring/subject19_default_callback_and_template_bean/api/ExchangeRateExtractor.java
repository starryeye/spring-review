package dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExchangeRateExtractor {

    BigDecimal extract(String quoteCurrency, String response) throws JsonProcessingException;
}
