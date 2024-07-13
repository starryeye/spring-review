package dev.starryeye.hellospring.subject24_application_service.data;

import dev.starryeye.hellospring.subject24_application_service.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager; // 필드 주입..

    public void save(Order order) {

        if(order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
    }
}
