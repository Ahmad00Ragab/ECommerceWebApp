package gov.iti.jets.app;

import gov.iti.jets.admin.Admin;
import gov.iti.jets.category.Category;
import gov.iti.jets.persistence.CustomPersistenceUnit;
import gov.iti.jets.product.Product;
import gov.iti.jets.product.ProductRepository;
import gov.iti.jets.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App {


     private static CustomPersistenceUnit cpu=new CustomPersistenceUnit();
    public static void main( String[] args )
    {


        try (EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(cpu, cpu.getProperties())) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Admin admin=new Admin("Ali","","743819fa", LocalDate.now(),LocalDate.now(),"Ali");
            em.persist(admin);
            em.getTransaction().commit();
            em.close();



        }
/*
        try {
            // Start transaction
            em.getTransaction().begin();

            // Perform ORM operations (example)
            // e.g., Creating and persisting a new User
            Category c = new Category("Antony", new Date(), new Date(), "Antony");
            em.persist(c);

            Product p = new Product("Antony", 100, "Antony description", 100, c, new Date(), new Date(), "Antony");
            em.persist(p);

            // Commit transaction
            em.getTransaction().commit();

            // Fetch and print the persisted user
            Product foundProduct = productRepository.getProductByName("Antony");
            System.out.println("Fetched User: " + foundProduct.getName());

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
            emf.close();
        }*/
    }
}