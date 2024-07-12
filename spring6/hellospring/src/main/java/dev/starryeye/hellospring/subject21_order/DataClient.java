package dev.starryeye.hellospring.subject21_order;

import dev.starryeye.hellospring.subject21_order.order.Order;
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
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin(); // 트랜잭션 시작

        Order order = Order.create("100", BigDecimal.TEN);
        em.persist(order); // 엔티티 영속화 (식별자 부여)
        log.info("order = {}", order);

        em.getTransaction().commit(); // 트랜잭션 커밋 (flush 포함)
        em.close();
    }
}
