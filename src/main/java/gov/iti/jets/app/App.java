package gov.iti.jets.app;

import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import gov.iti.jets.util.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;


import java.math.BigDecimal;
import java.time.LocalDateTime;



public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            // Create categories
            Category electronics = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
            Category clothing    = new Category("Clothing", LocalDateTime.now(), LocalDateTime.now());
            Category books       = new Category("Books", LocalDateTime.now(), LocalDateTime.now());

            // Persist categories
            em.persist(electronics);
            em.persist(clothing);
            em.persist(books);

            // Create products
            Product productA = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest smartphone", 100, electronics, LocalDateTime.now(), LocalDateTime.now());
            Product productB = new Product("T-shirt", BigDecimal.valueOf(360.99), "Cotton T-shirt", 50, clothing, LocalDateTime.now(), LocalDateTime.now());
            Product productC = new Product("Novel", BigDecimal.valueOf(500.99), "Bestselling novel", 200, books, LocalDateTime.now(), LocalDateTime.now());


            // Persist products
            em.persist(productA);
            em.persist(productB);
            em.persist(productC);

            /* Commit the transaction */ 
            em.getTransaction().commit();

            System.out.println("Data inserted successfully!");
        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error persisting data: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
