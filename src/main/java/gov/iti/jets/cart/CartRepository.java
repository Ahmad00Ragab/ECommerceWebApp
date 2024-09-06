package gov.iti.jets.cart;

import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.HashSet;
import java.util.Optional;
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

    public Set<CartItem> findCartByUserId(Long userId) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Create the query for CartItem class
            CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);

            // Define the root for the CartItem entity
            Root<CartItem> cartItemRoot = q.from(CartItem.class);

            // Join with the CartKey (cartId)
            Path<CartKey> cartKeyPath = cartItemRoot.get("cartId");

            // Build the query condition: userId from CartKey should match the given userId
            q.select(cartItemRoot)
                    .where(cb.equal(cartKeyPath.get("userId"), userId));

            // Execute the query and return the result
            return new HashSet<>(em.createQuery(q).getResultList());
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

    public Optional<CartItem> findById(CartKey cartId) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
            Root<CartItem> cartItem = q.from(CartItem.class);

            q.where(cb.equal(cartItem.get("cartId"), cartId));
            q.select(cartItem).distinct(true);

            return Optional.ofNullable(em.createQuery(q).getSingleResult());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching cart item with cartId: " + cartId, e);
        }
    }

    public void addCartItem(CartItem cartItem) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(cartItem);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding cart item: " + cartItem, e);
        }
    }

    public void deleteCartItem(CartKey cartId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            CartItem cartItem = em.find(CartItem.class, cartId);
            if (cartItem != null) {
                em.remove(cartItem);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting cart item with cartId: " + cartId, e);
        }
    }

    public void clearCartByUserId(Long userId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<CartItem> delete = cb.createCriteriaDelete(CartItem.class);
            Root<CartItem> cartItem = delete.from(CartItem.class);
            Path<CartKey> cartKey = cartItem.get("cartId");

            delete.where(cb.equal(cartKey.get("userId"), userId));

            em.createQuery(delete).executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while clearing cart for userId: " + userId, e);
        }
    }

}
