package gov.iti.jets.app;

import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Order;
import gov.iti.jets.models.OrderItem;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import gov.iti.jets.system.persistence.CustomPersistenceUnit;


import gov.iti.jets.util.CreateEntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




public class App {
public static void main(String[] args) {
    HibernatePersistenceProvider provider = new HibernatePersistenceProvider();

 /*   EntityManagerFactory emf = provider.createContainerEntityManagerFactory(new CustomPersistenceUnit(), new CustomPersistenceUnit().getProperties());
    EntityManager em = emf.createEntityManager();
    UserService userService= new UserService();*/
/*
   java.util.Set<User> userList=userService.findAll();
    userList.forEach(System.out::println);*/

    /*try {
        
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create and persist Category
        Category category = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
        em.persist(category);

        // Create and persist Product
        Product product = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now());
        em.persist(product);

        // Set product to category
        category.getProducts().add(product);

        // Create and persist User
        User user = new User("JohnDoe", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());
        em.persist(user);

        // Create and persist CartItem
        CartItem cart = new CartItem(user, product, 1);
        em.persist(cart);

        // Add CartItem to User
        user.getCartItems().add(cart);

        // Create and persist Order
        Order order = new Order(user, BigDecimal.valueOf(699.99), LocalDateTime.now());
        em.persist(order);

        // Create and persist OrderItem
        OrderItem orderItem = new OrderItem(order, product, 1, BigDecimal.valueOf(699.99));
        em.persist(orderItem);

        // Add OrderItem to Order
        order.getOrderItems().add(orderItem);

        em.getTransaction().commit();

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
    }*/


}

}
