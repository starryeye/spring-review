package dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.data;

import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order.Order;
import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager; // 필드 주입..

    @Override
    public void save(Order order) {

        if(order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
    }
}
