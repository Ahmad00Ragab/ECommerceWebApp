package gov.iti.jets.user;

import gov.iti.jets.cart.CartItem;
import gov.iti.jets.category.Category;
import gov.iti.jets.product.Product;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager em;

    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create User instances
        user1 = new User("ok", "jane@example.com", "password456", LocalDate.now(), LocalDate.now());
        user3 = new User("alice_smith", "alice@example.com", "password789", LocalDate.now(), LocalDate.now());

        // Create Product and Category instances
        Category category1 = new Category("Electronics", LocalDateTime.now(), LocalDateTime.now());
        Category category2 = new Category("Books", LocalDateTime.now(), LocalDateTime.now());

        // Create Product instances with the non-null constructor
        Product product1 = new Product("Smartphone", BigDecimal.valueOf(699.99), 50, category1, LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product("Laptop", BigDecimal.valueOf(999.99), 30, category1, LocalDateTime.now(), LocalDateTime.now());

        // Create Wishlist (Set<Product>) for user1
        Set<Product> wishlist = new HashSet<>();
        wishlist.add(product1);
        wishlist.add(product2);

        // Create User Interest (Set<Category>) for user1
        Set<Category> userInterests = new HashSet<>();
        userInterests.add(category1);
        userInterests.add(category2);

        // Assign cartItems, wishlist, and userInterests to user1
        user1.setWishlist(wishlist);
        user1.setCategories(userInterests);

        // Create CartItem instances
        CartItem cartItem1 = new CartItem(user1, product1, 2); // 2 units of product1 in user1's cart
        CartItem cartItem2 = new CartItem(user1, product2, 1); // 1 unit of product2 in user1's cart

        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        user1.setCartItems(cartItems);

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user3);

        // Mock the repository methods
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(any(User.class))).thenReturn(user1);
        when(userRepository.findByUsername("ok")).thenReturn(Optional.of(user1));
    }

    @AfterEach
    void tearDown() {
        userRepository = null;
    }

    @Test
    void findAll() {
        Set<User> users = userRepository.findAll();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        given(this.userRepository.findById(1L)).willReturn(Optional.of(user1));

        User foundUser = this.userRepository.findById(1L).get();

        assertNotNull(foundUser);
        assertEquals("ok", foundUser.getUsername());
    }

    @Test
    void findByIdNotFound() {
        // Given: Mock the behavior to throw an ObjectNotFoundException
        Long userId = 1L;
        given(userRepository.findById(Mockito.any(Long.class)))
                .willThrow(new ObjectNotFoundException("User", userId));

        // When: Call the method and capture the thrown exception
        Throwable thrown = catchThrowable(() -> {
            userRepository.findById(userId);
        });

        // Then: Assert that the correct exception is thrown with the right message
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find User with Id " + userId);

        // Verify that findById was called exactly once
        verify(userRepository, times(1)).findById(Mockito.any(Long.class));
    }

    @Test
    void save() {
        User newUser = new User("new_kid3", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());

        given(this.userRepository.save(newUser)).willReturn(newUser);
        given(this.userRepository.findByUsername("new_kid3")).willReturn(Optional.of(newUser));

        this.userRepository.save(newUser);

        User savedUser = userRepository.findByUsername("new_kid3").get();
        assertNotNull(savedUser);

        assertThat(savedUser.getUsername()).isEqualTo(newUser.getUsername());
        assertThat(savedUser.getPassword()).isEqualTo(newUser.getPassword());

        verify(this.userRepository, times(1)).save(newUser);
    }

    @Test
    void delete() {
        userRepository.delete(user1);
        verify(userRepository, times(1)).delete(user1);
    }

    @Test
    void update() {
        user1.setEmail("updated1@example.com");
        userRepository.update(user1);
        assertEquals("updated1@example.com", user1.getEmail());

        verify(userRepository, times(1)).update(user1);
    }

    @Test
    void getUserByUsername() {
        User foundUser = userRepository.findByUsername("ok").get();
        assertNotNull(foundUser);
        assertEquals("ok", foundUser.getUsername());
    }
}