package gov.iti.jets.user;

import gov.iti.jets.cart.CartItem;
import gov.iti.jets.category.Category;
import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.product.Product;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Optional;
import java.util.Set;

public class UserRepository extends GenericDaoImpl<User> {

    public UserRepository() {
        super(User.class);
    }

    public Optional<User> findByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> q = cb.createQuery(User.class);

            Root<User> user = q.from(User.class);

            q.where(cb.equal(user.get("username"), username));

            q.select(user).distinct(true);

            return Optional.ofNullable(em.createQuery(q).getSingleResult());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching " + username , e);
        }
    }

    public Set<Category> findInterestsByUserId(Long userId) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
        return user.getInterests();
    }

    public Set<Product> findWishlistByUserId(Long userId) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
        return user.getWishlist();
    }
    // interests
    public void addInterestToUser(Long userId, Category category) {
        User user = findById(userId).orElseThrow(() -> new ObjectNotFoundException("User", userId));
        user.getInterests().add(category);
        update(user);
    }

    public void removeInterestFromUser(Long userId, Category category) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
        if (user.getInterests().contains(category)) {
            user.getInterests().remove(category);
            update(user);  // Save changes to the database
        } else {
            throw new ObjectNotFoundException("Interest", category.getId());
        }
    }
    // wishlist
    public void addProductToWishlist(Long userId, Product product) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
        user.getWishlist().add(product);
        update(user);
    }

    public void removeProductFromWishlist(Long userId, Product product) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
        user.getWishlist().remove(product);
        update(user);
    }
}