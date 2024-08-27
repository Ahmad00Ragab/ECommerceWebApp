package gov.iti.jets.app;

import gov.iti.jets.category.Category;
import gov.iti.jets.persistence.CustomPersistenceUnit;
import gov.iti.jets.product.Product;
import gov.iti.jets.product.ProductRepository;
import gov.iti.jets.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository(Product.class);
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = provider.createContainerEntityManagerFactory(new CustomPersistenceUnit(), null);

        // Create EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Start transaction
            em.getTransaction().begin();

            // Perform ORM operations (example)
            // e.g., Creating and persisting a new User
            Category c = new Category("Antony2", new Date(), new Date(), "Antony");
            em.persist(c);

            Product p = new Product("Antony2", 100, "Antony description", 100, c, new Date(), new Date(), "Antony");
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
        }
    }
}