package gov.iti.jets.app;

import gov.iti.jets.models.Admin;
import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.services.AdminService;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.util.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;


public class App {
public static void main(String[] args) {

//    EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
//    EntityManager em = emf.createEntityManager();
//    try {
//
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        // Create and persist Category
//        Category category = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
//        em.persist(category);
//
//        // Create and persist Product
//        Product product = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now());
//        em.persist(product);
//
//        // Set product to category
//        category.getProducts().add(product);
//
//        // Create and persist User
//        User user = new User("JohnDoe", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());
//        em.persist(user);
//
//        // Create and persist CartItem
//        CartItem cart = new CartItem(user, product, 1);
//        em.persist(cart);
//
//        // Add CartItem to User
//        user.getCartItems().add(cart);
//
//        // Create and persist Order
//        Order order = new Order(user, BigDecimal.valueOf(699.99), LocalDateTime.now());
//        em.persist(order);
//
//        // Create and persist OrderItem
//        OrderItem orderItem = new OrderItem(order, product, 1, BigDecimal.valueOf(699.99));
//        em.persist(orderItem);
//
//        // Add OrderItem to Order
//        order.getOrderItems().add(orderItem);
//
//        em.getTransaction().commit();
//
//        System.out.println("Data persisted successfully!");
//        System.out.println("User: " + user.getUsername());
//        System.out.println("User: " + user.getCreditLimit());
//        System.out.println("User: " + user.getPassword());
//        System.out.println("User: " + user.getPhone());
//
//    } catch (PersistenceException e) {
//        if (em != null && em.getTransaction().isActive()) {
//            em.getTransaction().rollback();
//        }
//        System.err.println("Error persisting data: " + e.getMessage());
//        e.printStackTrace();
//    } finally {
//        if (em != null && em.isOpen()) {
//            em.close();
//        }
//        if (emf != null && emf.isOpen()) {
//            emf.close();
//        }
//
//    }



    // Create an Admin
    Admin admin = new Admin(
        "Haroun",
        "haroun@gmail.com",
        "1234", // Encrypt the password
        LocalDateTime.now(),
        LocalDateTime.now(),
        "Haroun"
    );
    
    AdminService adminService = new AdminService();
    adminService.createAdmin(admin);

    
    UserService userService = new UserService();

    // Create 5 user objects
    User user1 = new User("john_doe", "John", "Doe", "john.doe@gmail.com", "Password123",
            "USA", "New York", "5th Ave", new BigDecimal("5000.00"),
            LocalDate.of(1985, 10, 10), "1234567890", LocalDate.now(), LocalDate.now());

    User user2 = new User("jane_smith", "Jane", "Smith", "jane.smith@outlook.com", "Password456",
            "UK", "London", "Oxford Street", new BigDecimal("3000.00"),
            LocalDate.of(1990, 3, 15), "0987654321", LocalDate.now(), LocalDate.now());

    User user3 = new User("mike_brown", "Mike", "Brown", "mike.brown@gmail.com", "Password789",
            "Canada", "Toronto", "Bloor Street", new BigDecimal("4000.00"),
            LocalDate.of(1988, 7, 22), "1112223333", LocalDate.now(), LocalDate.now());

    User user4 = new User("susan_clark", "Susan", "Clark", "susan.clark@outlook.com", "Password101",
            "Australia", "Sydney", "George Street", new BigDecimal("3500.00"),
            LocalDate.of(1995, 5, 12), "2223334444", LocalDate.now(), LocalDate.now());

    User user5 = new User("dan_lee", "Dan", "Lee", "dan.lee@gmail.com", "Password202",
            "USA", "San Francisco", "Market Street", new BigDecimal("4500.00"),
            LocalDate.of(1992, 8, 5), "3334445555", LocalDate.now(), LocalDate.now());

    userService.save(user1);
    userService.save(user2);
    userService.save(user3);
    userService.save(user4);
    userService.save(user5);



    //    ProductService productService = new ProductService();
//    CartService cartService = new CartService();
//
//    //User user = new User("tony", "tony@gmail.com", "123", LocalDate.now(), LocalDate.now());
//    User user = userService.findUserByUsername("tony").get();
//    Product product = productService.findProductById(1L);
//    CartItem cartItem = new CartItem(user, product, 2);
//
//    cartService.addProductToCart(user.getId(), product.getId(), 2);

    //System.out.println(userService.save(user));


        //     // // Persist products
        //     // em.persist(productA);
        //     // em.persist(productB);
        //     // em.persist(productC);

        //     /* Commit the transaction */ 
        //     em.getTransaction().commit();

        //     System.out.println("Data inserted successfully!");
        // } catch (PersistenceException e) {
        //     if (em != null && em.getTransaction().isActive()) {
        //         em.getTransaction().rollback();
        //     }
        //     System.err.println("Error persisting data: " + e.getMessage());
        //     e.printStackTrace();
        // } finally {
        //     if (em != null && em.isOpen()) {
        //         em.close();
        //     }
        //     if (emf != null && emf.isOpen()) {
        //         emf.close();
        //     }
        // }
    }
}

