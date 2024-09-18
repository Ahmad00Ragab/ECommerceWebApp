package gov.iti.jets.repositories.genericDao;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import gov.iti.jets.system.persistence.CreateEntityManagerFactory;

import gov.iti.jets.system.persistence.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.jpa.HibernatePersistenceProvider;

import gov.iti.jets.models.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

    private final Class<T> entityClass;


    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Set<T> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            query.from(entityClass);
            return new HashSet<>(em.createQuery(query).getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all records of " + entityClass.getSimpleName(), e);
        }
    }

   
    
    @Override
    public Optional<T> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching " + entityClass.getSimpleName() + " with ID: " + id, e);
        }
    }

    @Override
    public T save(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {

            em.persist(entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error saving entity: " + entityClass.getSimpleName(), e);
        }

    }

    public T update(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {

            T updatedEntity = em.merge(entity);
            return updatedEntity;

        } catch (Exception e) {
            throw new RuntimeException("Error updating entity: " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public boolean delete(Long id) {

        System.out.println("inside GenericDaoImp!");

        EntityManager em = EntityManagerUtil.getEntityManager();
        try {

            T entity = em.find(entityClass, id);
            if (entity == null) {
                throw new ObjectNotFoundException(entityClass.getSimpleName(), id);
            }
            em.remove(entity);

            return true;
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException("Error deleting entity: " + entityClass.getSimpleName(), e);  // Explicit rethrow of custom exception
        }
    }


    @Override
    public boolean delete(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.remove(entity);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting entity: " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return entity != null;
        } catch (Exception e) {
            throw new RuntimeException("Error checking if entity exists: " + entityClass.getSimpleName(), e);
        }
    }

    public long countWithNamedQuery() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.select(cb.count(root));
            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error counting entities: " + entityClass.getSimpleName(), e);
        }
    }

    public long countWithNamedQuery(String paramName, Object paramValue) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.where(cb.equal(root.get(paramName), paramValue));
            query.select(cb.count(root));
            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error counting entities with parameter: " + entityClass.getSimpleName(), e);

        }
    }
    // TODO Implement more methods here
}
