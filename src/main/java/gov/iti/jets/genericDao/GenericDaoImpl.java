package gov.iti.jets.genericDao;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import gov.iti.jets.util.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

    private final Class<T> entityClass;
    HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
    protected final EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
    protected EntityManager em;
    protected EntityTransaction transaction;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Set<T> findAll() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            query.from(entityClass);
            return new HashSet<>(em.createQuery(query).getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all records of " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching " + entityClass.getSimpleName() + " with ID: " + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T save(T entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving entity: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T update(T entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            T updatedEntity = em.merge(entity);
            transaction.commit();
            return updatedEntity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating entity: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            T entity = em.find(entityClass, id);
            if (entity == null) {
                throw new ObjectNotFoundException(entityClass.getSimpleName(), id);
            }
            em.remove(entity);
            transaction.commit();
            return true;
        } catch (ObjectNotFoundException e) {
            throw e;  // Explicit rethrow of custom exception
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting entity: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(T entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting entity: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean existsById(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            T entity = em.find(entityClass, id);
            return entity != null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public long countWithNamedQuery() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.select(cb.count(root));
            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error counting entities: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public long countWithNamedQuery(String paramName, Object paramValue) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.where(cb.equal(root.get(paramName), paramValue));
            query.select(cb.count(root));
            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error counting entities with parameter: " + entityClass.getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    // TODO Implement more methods here
}
