package gov.iti.jets.repositories;

import gov.iti.jets.services.dtos.ProductDto;
import gov.iti.jets.repositories.genericDao.GenericDaoImpl;
import gov.iti.jets.models.Product;
import gov.iti.jets.system.persistence.EntityManagerUtil;
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



    public Set<Product> sortProductsByPrice() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            String sortByPriceQuery = "SELECT p FROM Product p ORDER BY p.price ASC";
            Query query = em.createQuery(sortByPriceQuery);
            return new HashSet<>(query.getResultList());
        }catch (Exception e) {
            return Collections.emptySet();
        }
    }


    public Set<Product> sortProductsByCategoryAndPrice(String category) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            String sortProductCat = "SELECT p FROM Product p JOIN p.category c WHERE c.name = :category ORDER BY p.price ASC";
            TypedQuery<Product> query = em.createQuery(sortProductCat, Product.class);
            query.setParameter("category", category);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            return Collections.emptySet();
        }

    }


    public Set<ProductDto> searchShoeByName(String name, int pageNumber, int pageSize) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

            // Construct the ProductDto
            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price"),
                    productRoot.get("stock")
            ));

            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            TypedQuery<ProductDto> query = em.createQuery(cq);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return new HashSet<>(query.getResultList());
        }
        catch (Exception e) {
            return Collections.emptySet();
        }
    }

    public Set<ProductDto> filterProducts(String category, String size, String color,
                                          BigDecimal minPrice, BigDecimal maxPrice, String sortOrder,
                                          int pageNumber, int pageSize) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProductDto> cq = cb.createQuery(ProductDto.class);
            Root<Product> productRoot = cq.from(Product.class);

        
            cq.select(cb.construct(ProductDto.class,
                    productRoot.get("id"),
                    productRoot.get("name"),
                    productRoot.get("description"),
                    productRoot.get("imageUrl"),
                    productRoot.get("price"),
                    productRoot.get("stock")
            ));

           
            return getProductDtos(category, size, color, minPrice, maxPrice, sortOrder, pageNumber, pageSize, cb, productRoot, cq, em);

        } catch (Exception e) {
            return Collections.emptySet();
        }
    }

    private static HashSet<ProductDto> getProductDtos(String category, String size, String color, BigDecimal minPrice, BigDecimal maxPrice, String sortOrder, int pageNumber, int pageSize, CriteriaBuilder cb, Root<Product> productRoot, CriteriaQuery<ProductDto> cq, EntityManager em) {
        List<Predicate> predicates = new ArrayList<>();

        if (category != null && !category.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("category").get("name"), category));
        }

   
        if (size != null && !size.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("shoeSize"), size));
        }

       
        if (color != null && !color.isEmpty()) {
            predicates.add(cb.equal(productRoot.get("shoeColor"), color));
        }

      
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(productRoot.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(productRoot.get("price"), maxPrice));
        }

       
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Normalize sortOrder to handle case-insensitivity
        if (sortOrder != null) {
            sortOrder = sortOrder.toLowerCase();
        }

   
        if ("asc".equals(sortOrder)) {
            cq.orderBy(cb.asc(productRoot.get("price")));  // Sort by price ascending
        } else if ("desc".equals(sortOrder)) {
            cq.orderBy(cb.desc(productRoot.get("price")));  // Sort by price descending
        } else {
         
            cq.orderBy(cb.asc(productRoot.get("name")));
        }

        TypedQuery<ProductDto> query = em.createQuery(cq);

    
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new HashSet<>(query.getResultList());
    }


    ///  counting products processes

    // count the number of shoes that matches a name
    public int countProductsByName(String name) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> productRoot = cq.from(Product.class);

            cq.select(cb.count(productRoot));
            cq.where(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));

            TypedQuery<Long> query = em.createQuery(cq);
            return query.getSingleResult().intValue();

        } catch (Exception e) {
            return 0;
        }
    }


    public int countFilteredProducts(String category, String size, String color,
                                     BigDecimal minPrice, BigDecimal maxPrice) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> productRoot = cq.from(Product.class);

           
            cq.select(cb.count(productRoot));

            
            List<Predicate> predicates = new ArrayList<>();

          
            if (category != null && !category.isEmpty()) {
                predicates.add(cb.equal(productRoot.get("category").get("name"), category));
            }

         
            if (size != null && !size.isEmpty()) {
                predicates.add(cb.equal(productRoot.get("shoeSize"), size));
            }

           
            if (color != null && !color.isEmpty()) {
                predicates.add(cb.equal(productRoot.get("shoeColor"), color));
            }

        
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(productRoot.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(productRoot.get("price"), maxPrice));
            }

         
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

           
            TypedQuery<Long> query = em.createQuery(cq);
            return query.getSingleResult().intValue();

        } catch (Exception e) {
            return 0;
        }

    }

}
