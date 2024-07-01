package dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment;

import dev.starryeye.hellospring.subject10_dependency_inversion_principle.exchangerate.WebApiExchangeRateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @DisplayName("baseCurrency, foreignCurrencyAmount 를 전달하면 현재 USD/KRW 환율로 KRW 돈으로 환산하고 유효시간 30분을 측정하여 Payment 객체가 반환된다.")
    @Test
    void prepare() throws IOException {

        // given
        PaymentService paymentService = new PaymentService(new WebApiExchangeRateProvider());
        Long orderId = 1L;
        String baseCurrency = "USD";
        BigDecimal foreignCurrencyAmount = BigDecimal.TEN;

        // when
        Payment result = paymentService.prepare(orderId, baseCurrency, foreignCurrencyAmount);

        // then
        assertThat(result.getExchangeRate()).isNotNull(); // 현재 환율 정보 (USD/KRW)

        assertThat(result.getConvertedAmount())
                .isEqualTo(result.getExchangeRate().multiply(foreignCurrencyAmount)); // 원화 환산

        assertThat(result.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(result.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

}