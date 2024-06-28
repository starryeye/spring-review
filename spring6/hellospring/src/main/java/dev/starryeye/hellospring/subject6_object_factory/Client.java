package dev.starryeye.hellospring.subject6_object_factory;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {

        /**
         * [6. 오브젝트 팩토리]
         * 사실, [5. 관계구성(설정) 책임의 분리] 에서 Client 클래스도 관심사 분리가 필요하였다.
         * PaymentService 처럼 해결이 필요하여, 의존관계를 구성하는 역할을 담당하는 클래스(Object Factory) 를 만들었다.
         */

        ObjectFactory objectFactory = new ObjectFactory();
        PaymentService paymentService = objectFactory.paymentService();

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
