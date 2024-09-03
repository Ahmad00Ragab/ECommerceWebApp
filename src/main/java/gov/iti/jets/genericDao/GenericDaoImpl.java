package gov.iti.jets.genericDao;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
    public T findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            T entity = em.find(entityClass, id);
            if(entity == null){
                throw new EntityNotFoundException();
            }else {
                return entity;
            }
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
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
            e.printStackTrace();
        }
        return entity;
    }

    public T update(T entity) {

        try (EntityManager em = emf.createEntityManager()){
            transaction = em.getTransaction();
            transaction.begin();
            T updatedEntity = em.merge(entity);
            transaction.commit();
            return updatedEntity;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public boolean delete(Long id) {
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
        return true;
    }

    @Override
    public boolean delete(T entity) {
        try (EntityManager em = emf.createEntityManager()) {
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public long countWithNamedQuery() {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);

            query.select(cb.count(root));

            return (long) em.createQuery(query).getSingleResult();
        }
    }

    public long countWithNamedQuery(String paramNam, Object paramValue) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.where(cb.equal(root.get(paramNam), paramValue));
            query.select(cb.count(root));
            return (long) em.createQuery(query).getSingleResult();
        }
    }

    // TODO Implement more methods here
}