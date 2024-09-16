package gov.iti.jets.repositories;

import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoryRepository extends GenericDaoImpl<Category> {

    EntityManager em = emf.createEntityManager();

    public CategoryRepository() {
        super(Category.class);
    }


    public Set<Category> findAll() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class);
            Root<Category> categoryRoot = cq.from(Category.class);

            // Select all categories and order by name in ascending order
            cq.select(categoryRoot).orderBy(cb.asc(categoryRoot.get("name")));

            TypedQuery<Category> query = em.createQuery(cq);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
          //  e.printStackTrace();  // Log exception for debugging
            return Collections.emptySet();  // Return empty set in case of an exception
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
