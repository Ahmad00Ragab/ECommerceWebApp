package gov.iti.jets.repositories;

import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;



public class CategoryRepository extends GenericDaoImpl<Category> {

    EntityManager em = emf.createEntityManager();

    public CategoryRepository(Class entityClass) {
        super(entityClass);
    }

    public List<Product> getProductsByCategory(String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);

        Root<Category> categoryRoot = q.from(Category.class);
        Join<Category, Product> products = categoryRoot.join("products");
        q.where(cb.equal(categoryRoot.get("name"), category));
        q.select(products);

        return em.createQuery(q).getResultList();
    }


}
