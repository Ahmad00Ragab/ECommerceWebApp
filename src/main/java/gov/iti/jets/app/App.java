package gov.iti.jets.app;

import gov.iti.jets.cart.CartItem;
import gov.iti.jets.category.Category;
import gov.iti.jets.order.Order;
import gov.iti.jets.order.OrderItem;
import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class App {
    private static final CustomPersistenceUnit cpu = new CustomPersistenceUnit();
       public static void main(String[] args) {
/*
        try (EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(cpu, cpu.getProperties())){
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
           User user =  new User("new_user4", "new@exampl4.com", "password000", LocalDate.now(), LocalDate.now());
           //em.persist(user);
            Category category = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
            em.persist(category);
            Product product = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now(),"Joe Doe");
           // em.persist(product);
            Cart cart = new Cart(user, product, 1);
            Set<Cart> carts = new HashSet<>();
            carts.add(cart);
            user.setCart(carts);
            em.persist(user);
           em.getTransaction().commit();





           *//* // Create and persist Category


            // Create and persist Product
            Product product = new Product("Smartphone", BigDecimal.valueOf(699.99), "Latest model", 50, category, LocalDateTime.now(), LocalDateTime.now());
            em.persist(product);

            // Update Category with Products
            Set<Product> products = new HashSet<>();
            products.add(product);
            category.setProducts(products);

            em.merge(category); // Ensure updates to category are persisted

            // Create and persist Admin
            Admin admin = new Admin("Ali", "ali@gmail.com", "743819fa", LocalDateTime.now(), LocalDateTime.now(), "Ali");
            em.persist(admin);

            // Create and persist User
            User user = new User("JohnDoe", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());
            em.persist(user);

            // Create and persist Cart
            Cart cart = new Cart(user, product, 1);
            em.persist(cart);

            // Update User with Cart
            Set<Cart> carts = new HashSet<>();
            carts.add(cart);
            user.setCart(carts);

            em.merge(user); // Ensure updates to user are persisted

            // Create and persist Order
            Order order = new Order(user, BigDecimal.valueOf(699.99), LocalDateTime.now());
            em.persist(order);

            // Create and persist OrderItem
            OrderItem orderItem = new OrderItem(order, product, 1, BigDecimal.valueOf(699.99));
            em.persist(orderItem);

            // Update Order with OrderItems
            Set<OrderItem> orderItems = new HashSet<>();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);

            em.merge(order);  Ensure updates to order are persisted

          //  em.getTransaction().commit();*//*
            em.close();
        } catch (Exception x) {
        } */
 
    }
}
