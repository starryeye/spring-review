package dev.starryeye.hellospring.subject22_order_repository_and_exception;

import dev.starryeye.hellospring.subject22_order_repository_and_exception.data.OrderRepository;
import dev.starryeye.hellospring.subject22_order_repository_and_exception.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order1 = Order.create("100", BigDecimal.TEN);
        orderRepository.save(order1);

        Order order2 = Order.create("100", BigDecimal.TEN);
        orderRepository.save(order2); // exception 발생!! no 는 유니크 제약조건이 DB 에 걸려있다.
    }
}
