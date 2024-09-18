package gov.iti.jets.repositories;

import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.CartKey;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.system.persistence.EntityManagerUtil;
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
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
            Root<CartItem> cart = q.from(CartItem.class);
            q.where(cb.equal(cart.get("user"), user));
            return em.createQuery(q).getResultStream().collect(Collectors.toSet());
        }catch (Exception e){
            throw new RuntimeException("Error occurred while fetching cart for user: " + user, e);
        }
    }

    

    public Set<CartItem> findCartByUserId(Long userId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
            Root<CartItem> cartItemRoot = q.from(CartItem.class);
            Path<CartKey> cartKeyPath = cartItemRoot.get("cartId");
            q.select(cartItemRoot)
                    .where(cb.equal(cartKeyPath.get("userId"), userId));
            return new HashSet<>(em.createQuery(q).getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching cart for userId: " + userId, e);
        }
    }

    public boolean delete(CartKey key) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            em.remove(em.find(CartItem.class, key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //not working...........
    public Optional<CartItem> findById(CartKey cartId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {

            // Simply use `find()` method with the composite key (CartKey)
            CartItem cartItem = em.find(CartItem.class, cartId);

            // Return as an Optional
            return Optional.ofNullable(cartItem);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching cart item with cartId: " + cartId, e);
        }
    }


    public void addCartItem(Long userId, Long productId, int quantity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            User user = em.find(User.class, userId);
            Product product = em.find(Product.class, productId);

            CartItem cartItem = new CartItem();
            cartItem.setCartId(new CartKey(userId, productId));
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            em.merge(cartItem);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding cart item: " + e);
        }
    }

    public void deleteCartItem(CartKey cartId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CartItem cartItem = em.find(CartItem.class, cartId);
            if (cartItem != null) {
                em.remove(cartItem);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting cart item with cartId: " + cartId, e);
        }
    }

    public void clearCartByUserId(Long userId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaDelete<CartItem> delete = cb.createCriteriaDelete(CartItem.class);

            Root<CartItem> cartItem = delete.from(CartItem.class);

            Path<CartKey> cartKey = cartItem.get("cartId");

            delete.where(cb.equal(cartKey.get("userId"), userId));

            em.createQuery(delete).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while clearing cart for userId: " + userId, e);
        }
    }

    // findById not working
//    public boolean exists(CartKey cartId) {
//        return findById(cartId).isPresent();
//    }
}
