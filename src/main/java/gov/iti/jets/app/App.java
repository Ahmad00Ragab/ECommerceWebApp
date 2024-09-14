    package gov.iti.jets.app;

import gov.iti.jets.models.Admin;
import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.OrderItem;
import gov.iti.jets.models.Order;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.services.AdminService;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.OrderService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.system.persistence.CreateEntityManagerFactory;
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

                // EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();
                // EntityManager em = emf.createEntityManager();
                // try {
                //
                // em = emf.createEntityManager();
                // em.getTransaction().begin();
                //
                // // Create and persist Category
                // Category category = new Category("Electronics", LocalDateTime.now(),
                // LocalDateTime.now());
                // em.persist(category);
                //
                // // Create and persist Product
                // Product product = new Product("Smartphone", BigDecimal.valueOf(699.99),
                // "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now());
                // em.persist(product);
                //
                // // Set product to category
                // category.getProducts().add(product);
                //
                // // Create and persist User
                // User user = new User("JohnDoe", "john.doe@gmail.com", "password123",
                // LocalDate.now(), LocalDate.now());
                // em.persist(user);
                //
                // // Create and persist CartItem
                // CartItem cart = new CartItem(user, product, 1);
                // em.persist(cart);
                //
                // // Add CartItem to User
                // user.getCartItems().add(cart);
                //
                // // Create and persist Order
                // Order order = new Order(user, BigDecimal.valueOf(699.99),
                // LocalDateTime.now());
                // em.persist(order);
                //
                // // Create and persist OrderItem
                // OrderItem orderItem = new OrderItem(order, product, 1,
                // BigDecimal.valueOf(699.99));
                // em.persist(orderItem);
                //
                // // Add OrderItem to Order
                // order.getOrderItems().add(orderItem);
                //
                // em.getTransaction().commit();
                //
                // System.out.println("Data persisted successfully!");
                // System.out.println("User: " + user.getUsername());
                // System.out.println("User: " + user.getCreditLimit());
                // System.out.println("User: " + user.getPassword());
                // System.out.println("User: " + user.getPhone());
                //
                // } catch (PersistenceException e) {
                // if (em != null && em.getTransaction().isActive()) {
                // em.getTransaction().rollback();
                // }
                // System.err.println("Error persisting data: " + e.getMessage());
                // e.printStackTrace();
                // } finally {
                // if (em != null && em.isOpen()) {
                // em.close();
                // }
                // if (emf != null && emf.isOpen()) {
                // emf.close();
                // }
                //
                // }


                /* ================ Code To Populate Some Data into The DataBase  ============ */

                AdminService   adminService   = new AdminService();
                UserService    userService    = new UserService();
                OrderService   orderService   = new OrderService();
                ProductService productService = new ProductService();
                CategoryService categeoryService = new CategoryService();
                

                // Create an Admin
                Admin admin = new Admin(
                                "Haroun",
                                "haroun@gmail.com",
                                "1234", 
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                "Haroun");

                adminService.createAdmin(admin);

                
                
                // Create Users with additional fields from the new User model
                User haroun = new User("Haroun00Smith", "Haroun", "Smith", "haroun@example.com", "Password123",
                                "USA", "New York", "5th Avenue", new BigDecimal("5000"), LocalDate.of(1990, 1, 1),
                                "555-1234", LocalDate.now(), LocalDate.now());

                User tony = new User("Tony00Johnson", "Tony", "Johnson", "tony@example.com", "Password123",
                                "USA", "Los Angeles", "Sunset Blvd", new BigDecimal("4500"), LocalDate.of(1985, 3, 15),
                                "555-5678", LocalDate.now(), LocalDate.now());

                User ghandy = new User("Ghandy00Brown", "Ghandy", "Brown", "ghandy@example.com", "Password123",
                                "USA", "Chicago", "Lake Shore Drive", new BigDecimal("4000"), LocalDate.of(1992, 5, 20),
                                "555-6789", LocalDate.now(), LocalDate.now());

                User alice = new User("Alice00Davis", "Alice", "Davis", "alice@example.com", "Password123",
                                "USA", "Miami", "Ocean Drive", new BigDecimal("3500"), LocalDate.of(1995, 7, 30),
                                "555-9876", LocalDate.now(), LocalDate.now());

                User bob = new User("Bob00Miller", "Bob", "Miller", "bob@example.com", "Password123",
                                "USA", "Houston", "Main St", new BigDecimal("3000"), LocalDate.of(1988, 9, 10),
                                "555-4321", LocalDate.now(), LocalDate.now());

                // Save Users
                userService.save(haroun);
                userService.save(tony);
                userService.save(ghandy);
                userService.save(alice);
                userService.save(bob);

                // Create Categories
                Category menShoes = new Category("Men's Shoes", LocalDateTime.now(), LocalDateTime.now());
                Category womenShoes = new Category("Women's Shoes", LocalDateTime.now(), LocalDateTime.now());
                Category kidsShoes = new Category("Kids' Shoes", LocalDateTime.now(), LocalDateTime.now());
                Category sportsShoes = new Category("Sports Shoes", LocalDateTime.now(), LocalDateTime.now());
                Category luxurySneakers = new Category("Luxury Sneakers", LocalDateTime.now(), LocalDateTime.now());
                Category casualSneakers = new Category("Casual Sneakers", LocalDateTime.now(), LocalDateTime.now());

                categeoryService.saveCategory(menShoes);
                categeoryService.saveCategory(womenShoes);
                categeoryService.saveCategory(kidsShoes);
                categeoryService.saveCategory(sportsShoes);
                categeoryService.saveCategory(luxurySneakers);
                categeoryService.saveCategory(casualSneakers);


                // Create Products
                Product product1 = new Product("Nike Air", new BigDecimal("150"), 10, menShoes, LocalDateTime.now(),
                                LocalDateTime.now());
                Product product2 = new Product("Adidas Ultraboost", new BigDecimal("180"), 15, sportsShoes,
                                LocalDateTime.now(), LocalDateTime.now());
                Product product3 = new Product("Puma Sneakers", new BigDecimal("120"), 8, casualSneakers,
                                LocalDateTime.now(), LocalDateTime.now());

                productService.saveProduct(product1);
                productService.saveProduct(product2);
                productService.saveProduct(product3);
        

                // Create Orders
                Order order1 = new Order(haroun, new BigDecimal("300"), LocalDateTime.now());
                Order order2 = new Order(tony, new BigDecimal("150"), LocalDateTime.now());
                Order order3 = new Order(ghandy, new BigDecimal("250"), LocalDateTime.now());
                
                Order order4 = new Order(alice, new BigDecimal("400"), LocalDateTime.now());
                Order order5 = new Order(bob, new BigDecimal("500"), LocalDateTime.now());



                // Add Order Items
                OrderItem item1 = new OrderItem(order1, product1, 2, product1.getPrice());
                OrderItem item2 = new OrderItem(order2, product2, 1, product2.getPrice());
                OrderItem item3 = new OrderItem(order3, product3, 3, product3.getPrice());

                order1.getOrderItems().add(item1);
                order2.getOrderItems().add(item2);
                order3.getOrderItems().add(item3);

        
                orderService.createOrder(order1);
                orderService.createOrder(order2);
                orderService.createOrder(order3);
                orderService.createOrder(order4);
                orderService.createOrder(order5);

                //User user = new User("tony", "tony@gmail.com", "123", LocalDate.now(), LocalDate.now());
                //    User user = userService.findUserByUsername("tony").get();
                //    Product product = productService.findProductById(1L).get();
                //    CartItem cartItem = new CartItem(user, product, 2);
                //
                //    cartService.addProductToCart(user.getId(), product.getId(), 3);

                // System.out.println(userService.save(user));

                // // // Persist products
                // // em.persist(productA);
                // // em.persist(productB);
                // // em.persist(productC);

                // /* Commit the transaction */
                // em.getTransaction().commit();

                // System.out.println("Data inserted successfully!");
                // } catch (PersistenceException e) {
                // if (em != null && em.getTransaction().isActive()) {
                // em.getTransaction().rollback();
                // }
                // System.err.println("Error persisting data: " + e.getMessage());
                // e.printStackTrace();
                // } finally {
                // if (em != null && em.isOpen()) {
                // em.close();
                // }
                // if (emf != null && emf.isOpen()) {
                // emf.close();
                // }
                // }
        }
}
