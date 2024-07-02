package dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.subject13_spring_test;

import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.Payment;
import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
//@Import(TestObjectFactory.class)
class PaymentServiceSpringTest {

    /**
     * subject 12 에서는 수동으로 DI 를 직접해주었지만,
     * subject 13 에서는 Spring DI 를 이용하여 테스트를 한다. (컨테이너가 구성해준 PaymentService 를 사용)
     *
     * 참고
     * 원래 지식으로는 @TestConfiguration + @Import + @SpringBootTest 였는데..
     * -> Application 전체 빈 로딩 + @Import 의 빈들 추가
     * 좀더 가볍게 사용을 원하면
     * @TestConfiguration + @ContextConfiguration + @ExtendWith(SpringExtension.class)
     * -> Spring container 빈깡통 + @ContextConfiguration 의 빈들 추가
     * 로 사용하면 된다..
     *
     * 참고
     * 현재 프로젝트에 @SpringBootApplication 이 없기 때문에..
     * @SpringBootTest 를 사용하지 못하는 상태이다.
     */

    @Autowired
    private PaymentService paymentService;

    @Test
    void convertedAmount() throws IOException {

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
    }


}