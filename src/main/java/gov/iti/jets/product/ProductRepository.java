package gov.iti.jets.product;

import gov.iti.jets.genericDao.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductRepository extends GenericDaoImpl<Product>{

    public ProductRepository(Class<Product> entityClass) {
        super(entityClass);
    }




    public Optional<Product> getProductByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Product> q = cb.createQuery(Product.class);

            Root<Product> productRoot = q.from(Product.class);

            q.where(cb.equal(productRoot.get("name"), name));

            return Optional.of(em.createQuery(q).getSingleResult());
        }
    }



    public Set<Product> findProductsByCategory(String category) {
        String fetchingByCategory = "SELECT p FROM Product p WHERE p.category = :category";
        Query query = em.createQuery(fetchingByCategory);
        query.setParameter("category", category);
        return new HashSet<>(query.getResultList());
    }


    public Set<Product> findProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        String fetchingByPrice="SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice";
        Query query = em.createQuery(fetchingByPrice);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return new HashSet<>(query.getResultList());
    }



    public Set<Product> sortProductsByPrice() {
        String sortByPriceQuery= "SELECT p FROM Product p ORDER BY p.price ASC";
        Query query = em.createQuery(sortByPriceQuery);
        return new HashSet<>(query.getResultList());

    }


    public Set<Product> sortProductsByCategoryAndPrice(String category) {
        String sortProductCat = "SELECT p FROM Product p WHERE p.category.name = :category ORDER BY p.price ASC";
        TypedQuery<Product> query = em.createQuery(sortProductCat, Product.class);
        query.setParameter("category", category);
        return new HashSet<>(query.getResultList());
    }

}