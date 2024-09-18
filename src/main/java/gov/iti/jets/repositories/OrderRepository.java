package gov.iti.jets.repositories;

import gov.iti.jets.system.persistence.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.*;

import java.math.BigDecimal;

public class OrderRepository extends GenericDaoImpl<Order> {

    // Constructor to call the superclass constructor
    public OrderRepository() {
        super(Order.class);
    }

    public OrderRepository(Class entityClass) {
        super(entityClass);
    }

    // Find Order by total price method
    public Optional<Order> findByTotalPrice(BigDecimal totalPrice) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> orderRoot = cq.from(Order.class);
            Predicate pricePredicate = cb.equal(orderRoot.get("totalPrice"), totalPrice);
            cq.where(pricePredicate);

            TypedQuery<Order> query = em.createQuery(cq);

            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    




    // Find Orders by user ID method
    public List<Order> findOrdersByUserId(Long userId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> orderRoot = cq.from(Order.class);
            Predicate userPredicate = cb.equal(orderRoot.get("user").get("id"), userId);
            cq.where(userPredicate);
            TypedQuery<Order> query = em.createQuery(cq);
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

}
