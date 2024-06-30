package dev.starryeye.hellospring.subject7_spring_container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {

        /**
         * [7. 스프링 컨테이너와 의존관계 주입]
         * 스프링 컨테이너 (AnnotationConfigApplicationContext) 에 애플리케이션의 구성정보 (빈 생성, 런타임 의존관계) 를 넘겨주고
         * 스프링 컨테이너로 부터 런타임에 생성된 빈(PaymentService 타입)을 받을 수 있다.
         */

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
