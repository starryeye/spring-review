package dev.starryeye.hellospring.subject22_order_repository_and_exception.data;

import dev.starryeye.hellospring.subject22_order_repository_and_exception.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {

        /**
         * todo, 해보기
         * 아래 코드는 Template 만들기 정말 좋은 코드이다..
         * 바뀌는 부분은 em.persist(order); 이부분 뿐..
         */

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin(); // 트랜잭션 시작

        try {
            em.persist(order); // 엔티티 영속화 (식별자 부여)
//            em.flush(); // 이게 있으면 ConstraintViolationException 이 최종 예외이고 없으면 RollbackException 이 최종 예외이다.
            /**
             * ConstraintViolationException 은 Hibernate 예외..
             * RollbackException 은 JPA 예외.. (즉, 추상화 됨)
             */

            transaction.commit(); // 트랜잭션 커밋 (flush 포함)
        } catch (RuntimeException e) {

            if (transaction.isActive()) transaction.rollback(); // 트랜잭션 롤백
            throw e;
        } finally {

            if (em.isOpen()) em.close();
        }
    }
}
