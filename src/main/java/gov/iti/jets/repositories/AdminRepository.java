package gov.iti.jets.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Optional;
import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Admin;
import jakarta.persistence.criteria.*;



public class AdminRepository extends GenericDaoImpl<Admin> {

    // Remove EntityManager Instantiation: The AdminRepository class no longer directly
    // creates an EntityManager instance. Instead,it uses the EntityManager provided by the GenericDaoImpl methods.

    EntityManager em = emf.createEntityManager();

    // Constructor to call the superclass constructor
    public AdminRepository() {
        super(Admin.class);
    }
    public Optional<Admin> findByEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
            Root<Admin> adminRoot = cq.from(Admin.class);
            Predicate emailPredicate = cb.equal(adminRoot.get("email"), email);
            cq.where(emailPredicate);
            
            TypedQuery<Admin> query = em.createQuery(cq);
            
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



