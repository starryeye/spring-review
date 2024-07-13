package dev.starryeye.hellospring.subject28_transaction_proxy;

import dev.starryeye.hellospring.subject28_transaction_proxy.order.Order;
import dev.starryeye.hellospring.subject28_transaction_proxy.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class OrderClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = beanFactory.getBean(OrderService.class);

        Order order = service.createOrder("0100", BigDecimal.TEN);

        log.info(order.toString());
    }
}
