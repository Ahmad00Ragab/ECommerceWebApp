package gov.iti.jets.Category;

import gov.iti.jets.GenericDao.GenericDaoImpl;
import gov.iti.jets.Product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class CategoryRepository extends GenericDaoImpl {

    public CategoryRepository(Class entityClass, EntityManager entityManager, EntityTransaction transaction) {
        super(entityClass);
    }

    public List<Product> getProductsByCategory(String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> query = cb.createQuery(Category.class);
        query.from(Category.class);

        query.where(cb.equal(query.get("name"), category));


        return em.createQuery(query).getResultList();
    }
}
