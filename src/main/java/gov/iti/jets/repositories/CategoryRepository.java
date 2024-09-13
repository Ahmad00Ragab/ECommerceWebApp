package gov.iti.jets.repositories;

import gov.iti.jets.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoryRepository extends GenericDaoImpl<Category> {

    EntityManager em = emf.createEntityManager();

    public CategoryRepository() {
        super(Category.class);
    }


    // return all categories to ordered by name to display it in the home
    // page and do not change the order every request
    @Override
    public Set<Category> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Category> cq = cb.createQuery(Category.class);

        Root<Category> categoryRoot = cq.from(Category.class);

        cq.orderBy(cb.asc(categoryRoot.get("name")));

        List<Category> categories = em.createQuery(cq).getResultList();

        return new HashSet<>(categories);
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
