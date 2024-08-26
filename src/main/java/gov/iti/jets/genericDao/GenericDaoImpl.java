package gov.iti.jets.genericDao;

import gov.iti.jets.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Set;

public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

    private final Class<T> entityClass;
    HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
    protected final EntityManagerFactory emf = provider.createContainerEntityManagerFactory(new CustomPersistenceUnit(), null);
    protected EntityManager em;
    protected EntityTransaction transaction;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Set<T> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            query.from(entityClass);
            return (Set<T>) em.createQuery(query).getResultList();
        }
    }

    @Override
    public T findById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(entityClass, id);
        }
    }

    @Override
    public T save(T entity) {
        try (EntityManager em = emf.createEntityManager()) {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            transaction = em.getTransaction();
            transaction.begin();
            em.find(entityClass, id);
            em.remove(em.find(entityClass, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // TODO Implement more methods here
}