package dev.starryeye.hellospring.subject2_extension_through_inheritance;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchangeRatePaymentService extends PaymentService{
    @Override
    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        /**
         * [2. 상속을 통한 확장]
         * 상속을 통해 비즈니스 코드는 재사용하면서, 환율을 가져오는 다양한 방법을 만들 수 있다.
         */

        if (baseCurrency.equals("USD") && quoteCurrency.equals("KRW"))
            return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("지원되지 않는 통화쌍입니다.");
    }
}
