package dev.starryeye.hellospring.subject23_exception_abstraction.data;

import dev.starryeye.hellospring.subject23_exception_abstraction.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager; // 필드 주입..

    public void save(Order order) {
        entityManager.persist(order);
    }
}
