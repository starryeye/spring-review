package dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest2 {

    @DisplayName("baseCurrency, foreignCurrencyAmount 를 전달하면 현재 USD/KRW 환율로 KRW 돈으로 환산하고 유효시간 30분을 측정하여 Payment 객체가 반환된다.")
    @Test
    void prepare() throws IOException {

        // given
        BigDecimal exchangeRate = BigDecimal.valueOf(1300);
        Long orderId = 1L;
        String baseCurrency = "USD";
        BigDecimal foreignCurrencyAmount = BigDecimal.TEN;

        /**
         * stubbing 으로 처리함으로써 Test code 가 외부 API 에 의존하는 것을 없앨 수 있었다.
         * 또한, 원하는 환율 값으로 Test 할 수 있어서 기대 값과 정확하게 일치하는지 검증가능하도록 바뀌었다.
         *
         * 참고
         * Spring 이 제공하는 Dependency Injection 으로 의존성 관리를 하지 않았다면 stubbing 처리를 할 수 없었을 것이다.
         *
         * BigDecimal 을 다룰 때는 isEqualTo 가 아니라 isEqualByComparingTo 를 이용해야한다.
         */

        // stubbing
        PaymentService paymentService = new PaymentService(new ExchangeProviderStub(exchangeRate));

        // when
        Payment result = paymentService.prepare(orderId, baseCurrency, foreignCurrencyAmount);

        // then
        assertThat(result.getExchangeRate()).isEqualByComparingTo(exchangeRate); // 현재 환율 정보 (USD/KRW)

        assertThat(result.getConvertedAmount())
                .isEqualByComparingTo(exchangeRate.multiply(foreignCurrencyAmount)); // 원화 환산

        assertThat(result.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(result.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    void convertedAmount() throws IOException {

        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
        testAmount(BigDecimal.valueOf(3_500), BigDecimal.valueOf(35_000));
    }

    private static void testAmount(BigDecimal exchangeRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExchangeProviderStub(exchangeRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExchangeRate()).isEqualByComparingTo(exchangeRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}