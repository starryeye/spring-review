package dev.starryeye.hellospring.subject20_rest_template;

import dev.starryeye.hellospring.subject20_rest_template.payment.Payment;
import dev.starryeye.hellospring.subject20_rest_template.payment.PaymentService;
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
