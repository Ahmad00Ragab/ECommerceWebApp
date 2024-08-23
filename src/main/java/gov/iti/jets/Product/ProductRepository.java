package gov.iti.jets.Product;

import gov.iti.jets.GenericDao.GenericDaoImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductRepository extends GenericDaoImpl<Product> {

    public ProductRepository(Class<Product> entityClass) {
        super(entityClass);
    }

    public List<Product> getProductByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);

        Root<Product> productRoot = q.from(Product.class);

        q.where(cb.equal(productRoot.get("name"), name));

        return em.createQuery(q).getResultList();
    }
}