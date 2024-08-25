package gov.iti.jets.user;

import gov.iti.jets.genericDao.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserRepository extends GenericDaoImpl<User> {

    public UserRepository() {
        super(User.class);
    }

    public User getUserByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> q = cb.createQuery(User.class);

            Root<User> user = q.from(User.class);

            q.where(cb.equal(user.get("username"), username));

            q.select(user).distinct(true);

            return em.createQuery(q).getSingleResult();
        }
    }
}