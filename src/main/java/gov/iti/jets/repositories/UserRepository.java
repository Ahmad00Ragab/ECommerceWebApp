package gov.iti.jets.repositories;

import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.persistence.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> q = cb.createQuery(User.class);
            Root<User> user = q.from(User.class);
            q.where(cb.equal(user.get("username"), username));
            q.select(user).distinct(true);
            return Optional.ofNullable(em.createQuery(q).getSingleResult());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching " + username, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<User> findByEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> q = cb.createQuery(User.class);
            Root<User> user = q.from(User.class);
            q.where(cb.equal(user.get("email"), email));
            q.select(user).distinct(true);
            System.out.println(((em.createQuery(q).getSingleResult()).getEmail()));
            return Optional.ofNullable(em.createQuery(q).getSingleResult());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching " + email, e);
        } finally {
            em.close();
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


    // Interests
    public void addInterestToUser(Long userId, Category category) {
        User user = findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));
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


    // Wishlist
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
