package dev.starryeye.hellospring.subject17_extract_method;

import dev.starryeye.hellospring.subject17_extract_method.payment.Payment;
import dev.starryeye.hellospring.subject17_extract_method.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println("Payment : " + payment);
    }
}
