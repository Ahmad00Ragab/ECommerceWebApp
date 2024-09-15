package gov.iti.jets.repositories;

import gov.iti.jets.services.dtos.ProductDto;
import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.math.BigDecimal;
import java.util.*;

public class ProductRepository extends GenericDaoImpl<Product> {

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

            q.select(productRoot).where(cb.equal(productRoot.get("name"), name));

            return Optional.of(em.createQuery(q).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
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
            String fetchingByCategory = "FROM Product p JOIN p.category c WHERE c.name = :category";
            Query query = em.createQuery(fetchingByCategory);
            query.setParameter("category", category);
            return new HashSet<>(query.getResultList());
        }finally{
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
            String sortProductCat = "SELECT p FROM Product p JOIN p.category c WHERE c.name = :category ORDER BY p.price ASC";
            TypedQuery<Product> query = em.createQuery(sortProductCat, Product.class);
            query.setParameter("category", category);
            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

/*




    public Set<ProductDto> findProductByNameUsingProductDTO(String name, int pageNumber, int pageSize) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price")
            ));

            cq.where(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));

            TypedQuery<ProductDto> query = em.createQuery(cq);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Set<ProductDto> sortProductsByCategoryAndPriceUsingProductDTO(String category, int pageNumber, int pageSize) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);
            Join<Product, Category> categoryJoin = productRoot.join("category");

            cq.select(cb.construct(
                            ProductDto.class,
                            productRoot.get("id"),
                            productRoot.get("name"),
                            productRoot.get("description"),
                            productRoot.get("imageUrl"),
                            productRoot.get("price")
                    ))
                    .where(cb.equal(categoryJoin.get("name"), category))
                    .orderBy(cb.asc(productRoot.get("price")));

            TypedQuery<ProductDto> query = em.createQuery(cq);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return new HashSet<>(query.getResultList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   */




    public Set<ProductDto> filterProductsByName(String name, int pageNumber, int pageSize) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Construct the ProductDto
            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price")
            ));

            // List to hold the predicates
            List<Predicate> predicates = new ArrayList<>();

            // Apply name filter
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));
            }

            // Apply the predicate
            return applyPredicateProduct(pageNumber, pageSize, predicates, cq, em);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private static HashSet<ProductDto> applyPredicateProduct(int pageNumber, int pageSize, List<Predicate> predicates, CriteriaQuery<ProductDto> cq, EntityManager em) {
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Create and execute the query
        TypedQuery<ProductDto> query = em.createQuery(cq);

        // Pagination
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new HashSet<>(query.getResultList());
    }


    public Set<ProductDto> filterProducts(String category, String size, String color,
                                          BigDecimal minPrice, BigDecimal maxPrice,
                                          int pageNumber, int pageSize) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Construct the ProductDto
            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price")
            ));

            List<Predicate> predicates = getPredicates(category, size, color, minPrice, maxPrice, cb, productRoot);

            // Apply all predicates if there are any
            return applyPredicateProduct(pageNumber, pageSize, predicates, cq, em);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private static List<Predicate> getPredicates(String category, String size, String color, BigDecimal minPrice, BigDecimal maxPrice, CriteriaBuilder cb, Root<Product> productRoot) {
        // List to hold the dynamic predicates
        List<Predicate> predicates = new ArrayList<>();

        // Apply category filter
        if (category != null && !category.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("category").get("name"), category));
        }

        // Apply size filter
        if (size != null && !size.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("shoeSize"), size));
        }

        // Apply color filter
        if (color != null && !color.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("shoeColor"), color));
        }

        // Apply price filter
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(productRoot.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(productRoot.get("price"), maxPrice));
        }
        return predicates;
    }



    public Set<ProductDto> sortProducts(String sortOrder, int pageNumber, int pageSize) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Construct the ProductDto
            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price")
            ));


            if ("asc".equalsIgnoreCase(sortOrder)) {
                cq.orderBy(cb.asc(productRoot.get("price")));  // Sort by price ascending
            } else if ("desc".equalsIgnoreCase(sortOrder)) {
                cq.orderBy(cb.desc(productRoot.get("price")));  // Sort by price descending
            }

            TypedQuery<ProductDto> query = em.createQuery(cq);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return new HashSet<>(query.getResultList());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }




    public long countTotalProducts() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Select the count of all products
            cq.select(cb.count(productRoot));

            // Execute query
            return em.createQuery(cq).getSingleResult();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // count the number of shoes that matches a name

    public int countProductsByName(String name) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> productRoot = cq.from(Product.class);

            cq.select(cb.count(productRoot));
            cq.where(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));

            TypedQuery<Long> query = em.createQuery(cq);
            return query.getSingleResult().intValue();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    // count the filtered shoes

    public int countFilteredProducts(String category, String size, String color,
                                     BigDecimal minPrice, BigDecimal maxPrice) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Select count of products
            cq.select(cb.count(productRoot));

            // List to hold dynamic predicates
            List<Predicate> predicates = getPredicates(category, size, color, minPrice, maxPrice, cb, productRoot);

            // Apply all predicates if any exist
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            // Execute the query and return the result
            TypedQuery<Long> query = em.createQuery(cq);
            return query.getSingleResult().intValue();

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }


}