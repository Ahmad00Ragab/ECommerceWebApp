// package gov.iti.jets.cart;

// import gov.iti.jets.product.Product;
// import gov.iti.jets.user.User;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import static org.mockito.BDDMockito.given;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.Optional;
// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.*;

// class CartRepositoryTest {

//     @Mock
//     private CartRepository cartRepository;

//     @Mock
//     private User user;

//     @Mock
//     private Product product;

//     private CartItem cartItem;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         // Initialize sample data
//         user = new User("JohnDoe", "john.doe@gmail.com", "password123", LocalDate.now(), LocalDate.now());
//         product = new Product("Laptop", BigDecimal.valueOf(1000), "Gaming Laptop", 5, null, LocalDateTime.now(), LocalDateTime.now(), "Joe Doe");
//         cartItem = new CartItem(user, product, 2);
//     }

//     @AfterEach
//     void tearDown() {

//     }

//     @Test
//     void findById() {
//         // Given
//         CartKey cartKey = cartItem.getCartId(); // Create the CartKey from the CartItem
//         given(cartRepository.findById(cartKey)).willReturn(Optional.of(cartItem));

//         // When
//         Optional<CartItem> foundCart = cartRepository.findById(cartKey);

//         // Then
//         assertTrue(foundCart.isPresent()); // Ensure that the CartItem was found
//         assertEquals(cartItem.getCartId(), foundCart.get().getCartId()); // Check that the cartId matches
//         verify(cartRepository, times(1)).findById(cartKey); // Verify that the repository was called exactly once
//     }


//     @Test
//     void save() {
//         // Given
//         given(cartRepository.save(cartItem)).willReturn(cartItem);

//         // When
//         CartItem savedCart = cartRepository.save(cartItem);

//         // Then
//         assertNotNull(savedCart);
//         assertEquals(cartItem.getQuantity(), savedCart.getQuantity());
//         assertNotNull(savedCart.getCartId());
//         verify(cartRepository, times(1)).save(cartItem);
//     }

//     @Test
//     void delete() {
//         // Given
//         CartKey cartKey = cartItem.getCartId();
//         given(cartRepository.findById(cartKey)).willReturn(Optional.of(cartItem));

//         // When
//         cartRepository.delete(cartKey);

//         // Then
//         verify(cartRepository, times(1)).delete(cartKey);
//     }

//     @Test
//     void findByUser() {
//         // Given
//         given(cartRepository.findByUser(user)).willReturn(Set.of(cartItem));

//         // When
//         Set<CartItem> carts = cartRepository.findByUser(user);

//         // Then
//         assertFalse(carts.isEmpty());
//         assertEquals(1, carts.size());
//         //assertEquals(cartItem, carts.get(0));
//         verify(cartRepository, times(1)).findByUser(user);
//     }
// }