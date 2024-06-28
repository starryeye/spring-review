package dev.starryeye.hellospring.subject4_interface;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    /**
     * [4. 인터페이스를 도입]
     * [3. 클래스를 분리] 에서 구현체 변경을 하고 싶으면 기존 코드를 많이 변경했어야 했는데..
     * 인터페이스를 도입하고나서 메서드 규격이 정해져서
     * 실제 인터페이스 사용 코드는 더이상 수정을하지 않아도 되게 되었다. (자바의 다형성)
     *
     * 문제점..
     * 여전히 구현체를 변경하고 싶은데 기존 코드를 건드려야한다.. (SRP, OCP, DIP 위반)
     * - SRP : 비즈니스 로직이 변경될 때만 PaymentService 를 건드려야하나.. 구현체 변경시에도 변경해줘야함
     * - OCP : ExchangeRateProvider 가 추가 될 때(확장) 구현체를 변경해야 하므로 기존 코드(PaymentService) 를 건드려야함
     * - DIP : 인터페이스를 의존하는 것 처럼 보이지만, new WebApiExchangeRateProvider(); 코드로 인해 구현체에도 의존하고 있는 중이다. (이전 코드에서도 문제가 있었다.)
     */

    private final ExchangeRateProvider exchangeRateProvider;

    public PaymentService() {
        this.exchangeRateProvider = new WebApiExchangeRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        /**
         * [1. 관심사의 분리]
         * prepare 비즈니스 로직이 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency, "KRW");
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return Payment.builder()
                .orderId(orderId)
                .currency(currency)
                .foreignCurrencyAmount(foreignCurrencyAmount)
                .exchangeRate(exchangeRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }
}
