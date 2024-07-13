package dev.starryeye.hellospring.subject24_application_service;

import dev.starryeye.hellospring.subject24_application_service.data.OrderRepository;
import dev.starryeye.hellospring.subject24_application_service.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class) // OrderConfig 를 구성할 때, DataConfig 도 구성한다.
public class OrderConfig {

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }
}
