package gov.iti.jets.repositories;

import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductRepository extends GenericDaoImpl<Product>{

    public ProductRepository() {
        super(Product.class);
    }

    public Optional<Product> getProductByName(String name) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> q = cb.createQuery(Product.class);
            Root<Product> productRoot = q.from(Product.class);
            q.where(cb.equal(productRoot.get("name"), name));
            return Optional.of(em.createQuery(q).getSingleResult());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Set<Product> findProductsByCategory(String category) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String fetchingByCategory = "SELECT p FROM Product p WHERE p.category = :category";
            Query query = em.createQuery(fetchingByCategory);
            query.setParameter("category", category);
            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Set<Product> findProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String fetchingByPrice = "SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice";
            Query query = em.createQuery(fetchingByPrice);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Set<Product> sortProductsByPrice() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String sortByPriceQuery = "SELECT p FROM Product p ORDER BY p.price ASC";
            Query query = em.createQuery(sortByPriceQuery);
            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Set<Product> sortProductsByCategoryAndPrice(String category) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String sortProductCat = "SELECT p FROM Product p WHERE p.category.name = :category ORDER BY p.price ASC";
            TypedQuery<Product> query = em.createQuery(sortProductCat, Product.class);
            query.setParameter("category", category);
            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
