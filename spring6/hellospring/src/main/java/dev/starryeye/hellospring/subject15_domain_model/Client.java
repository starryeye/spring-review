package dev.starryeye.hellospring.subject15_domain_model;

import dev.starryeye.hellospring.subject15_domain_model.payment.Payment;
import dev.starryeye.hellospring.subject15_domain_model.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println("Payment 1 : " + payment1);

        System.out.println("----------------------------------------\n");

        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println("Payment 2 : " + payment2);

        System.out.println("----------------------------------------\n");

        TimeUnit.SECONDS.sleep(3);
        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println("Payment 3 : " + payment3);
    }
}
