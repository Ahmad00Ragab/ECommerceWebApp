package gov.iti.jets.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;

import java.util.Optional;

public class OrderRepository extends GenericDaoImpl<Order> {

    // Remove EntityManager Instantiation: The OrderRepository class no longer directly
    // creates an EntityManager instance. Instead, it uses the EntityManager provided by the GenericDaoImpl methods.

    EntityManager em = emf.createEntityManager();

    // Constructor to call the superclass constructor
    public OrderRepository() {
        super(Order.class);
    }

    // Find Order by total price method
    public Optional<Order> findByTotalPrice(BigDecimal totalPrice) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> orderRoot = cq.from(Order.class);
            Predicate pricePredicate = cb.equal(orderRoot.get("totalPrice"), totalPrice);
            cq.where(pricePredicate);

            TypedQuery<Order> query = em.createQuery(cq);

            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Find Orders by user 
    public Optional<Order> findByUser(Long userId) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> orderRoot = cq.from(Order.class);
            Predicate userPredicate = cb.equal(orderRoot.get("user").get("id"), userId);
            cq.where(userPredicate);

            TypedQuery<Order> query = em.createQuery(cq);

            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
