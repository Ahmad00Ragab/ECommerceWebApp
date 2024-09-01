package gov.iti.jets.cart;

import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Set;
import java.util.stream.Collectors;

public class CartRepository extends GenericDaoImpl<CartItem> {

    public CartRepository() {
        super(CartItem.class);
    }

    public Set<CartItem> findByUser(User user) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);

            Root<CartItem> cart = q.from(CartItem.class);

            q.where(cb.equal(cart.get("user"), user));

            return em.createQuery(q).getResultStream().collect(Collectors.toSet());
        }
    }

    public CartItem findById(CartKey cartId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(CartItem.class, cartId);
        }
    }

    public boolean delete(CartKey key) {
        try (EntityManager em = emf.createEntityManager()) {
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(em.find(CartItem.class, key));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
