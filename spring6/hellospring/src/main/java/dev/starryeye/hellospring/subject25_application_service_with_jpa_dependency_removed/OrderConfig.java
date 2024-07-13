package dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed;

import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.data.JpaOrderRepository;
import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order.OrderRepository;
import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order.OrderService;
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
        return new JpaOrderRepository();
    }
}
