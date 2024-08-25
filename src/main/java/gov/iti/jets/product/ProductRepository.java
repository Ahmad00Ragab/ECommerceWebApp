package gov.iti.jets.product;

import gov.iti.jets.genericDao.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductRepository extends GenericDaoImpl<Product> {

    public ProductRepository(Class<Product> entityClass) {
        super(entityClass);
    }

    public Product getProductByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Product> q = cb.createQuery(Product.class);

            Root<Product> productRoot = q.from(Product.class);

            q.where(cb.equal(productRoot.get("name"), name));

            return em.createQuery(q).getSingleResult();
        }
    }
}