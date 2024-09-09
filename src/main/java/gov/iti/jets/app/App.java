// // package gov.iti.jets.app;

// // import gov.iti.jets.models.CartItem;
// // import gov.iti.jets.models.Category;
// // import gov.iti.jets.models.Order;
// // import gov.iti.jets.models.OrderItem;
// // import gov.iti.jets.models.Product;
// // import gov.iti.jets.models.User;
// // import jakarta.persistence.EntityManager;
// // import jakarta.persistence.EntityManagerFactory;
// // import jakarta.persistence.PersistenceException;

// // import gov.iti.jets.util.CreateEntityManagerFactory;


// // import java.math.BigDecimal;
// // import java.time.LocalDate;
// // import java.time.LocalDateTime;
// // import java.util.HashSet;
// // import java.util.Set;



// // public class App {
// // public static void main(String[] args) {
// //     EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
// //     EntityManager em = emf.createEntityManager();
// //     try {
        
// //         em = emf.createEntityManager();
// //         em.getTransaction().begin();

// //         // Create and persist Category
// //         Category category = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
// //         em.persist(category);

// //         // Create and persist Product
// //         Product product = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now());
// //         em.persist(product);

// //         // Set product to category
// //         category.getProducts().add(product);

// //         // Create and persist User
// //         User user = new User("JohnDoe", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());
// //         em.persist(user);

// //         // Create and persist CartItem
// //         CartItem cart = new CartItem(user, product, 1);
// //         em.persist(cart);

// //         // Add CartItem to User
// //         user.getCartItems().add(cart);

// //         // Create and persist Order
// //         Order order = new Order(user, BigDecimal.valueOf(699.99), LocalDateTime.now());
// //         em.persist(order);

// //         // Create and persist OrderItem
// //         OrderItem orderItem = new OrderItem(order, product, 1, BigDecimal.valueOf(699.99));
// //         em.persist(orderItem);

// //         // Add OrderItem to Order
// //         order.getOrderItems().add(orderItem);

// //         em.getTransaction().commit();

// //     } catch (PersistenceException e) {
// //         if (em != null && em.getTransaction().isActive()) {
// //             em.getTransaction().rollback();
// //         }
// //         System.err.println("Error persisting data: " + e.getMessage());
// //         e.printStackTrace();
// //     } finally {
// //         if (em != null && em.isOpen()) {
// //             em.close();
// //         }
// //         if (emf != null && emf.isOpen()) {
// //             emf.close();
// //         }
// //     }
// // }

// // }





// package gov.iti.jets.app;

// import gov.iti.jets.models.Category;
// import gov.iti.jets.models.Product;
// import gov.iti.jets.util.CreateEntityManagerFactory;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.EntityManagerFactory;
// import jakarta.persistence.PersistenceException;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// public class App {
//     public static void main(String[] args) {
//         EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
//         EntityManager em = null;

//         try {
//             em = emf.createEntityManager();
//             em.getTransaction().begin();

//             // Create categories
//             Category electronics = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
//             Category clothing    = new Category("Clothing", LocalDateTime.now(), LocalDateTime.now());
//             Category books       = new Category("Books", LocalDateTime.now(), LocalDateTime.now());

//             // Persist categories
//             em.persist(electronics);
//             em.persist(clothing);
//             em.persist(books);

//             // Create products
//             Product productA = new Product("Smartphone", 699.99, "Latest smartphone", 100, electronics, LocalDateTime.now(), LocalDateTime.now());
//             Product productB = new Product("T-shirt", 29.99, "Cotton T-shirt", 50, clothing, LocalDateTime.now(), LocalDateTime.now());
//             Product productC = new Product("Novel", 9.99, "Bestselling novel", 200, books, LocalDateTime.now(), LocalDateTime.now());

//             // Persist products
//             em.persist(productA);
//             em.persist(productB);
//             em.persist(productC);

//             // Commit the transaction
//             em.getTransaction().commit();

//             System.out.println("Data inserted successfully!");
//         } catch (PersistenceException e) {
//             if (em != null && em.getTransaction().isActive()) {
//                 em.getTransaction().rollback();
//             }
//             System.err.println("Error persisting data: " + e.getMessage());
//             e.printStackTrace();
//         } finally {
//             if (em != null && em.isOpen()) {
//                 em.close();
//             }
//             if (emf != null && emf.isOpen()) {
//                 emf.close();
//             }
//         }
//     }
// }
