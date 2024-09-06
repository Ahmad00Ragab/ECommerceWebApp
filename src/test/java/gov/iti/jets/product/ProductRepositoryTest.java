package gov.iti.jets.product;


import gov.iti.jets.cart.CartItem;
import gov.iti.jets.category.Category;
import gov.iti.jets.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class ProductRepositoryTest {
    @Mock
    private  ProductRepository productRepository;
    private Product product1 ;
    private Product product2 ;
    private Product product3 ;
    Set<Product> products;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Category category= new Category("Product category", LocalDateTime.now(), LocalDateTime.now());
        product1 = new Product("Product 1",
                BigDecimal.valueOf(50.0),
                "Description for product 1",
                10,category, LocalDateTime.now(), LocalDateTime.now(),"Joe Doe");

        product2 = new Product("Product 2",
                BigDecimal.valueOf(75.0),
                "Description for product 2",
                10,category, LocalDateTime.now(), LocalDateTime.now(),"Joe Doe");

        product3 = new Product("Product 3",
                BigDecimal.valueOf(50.0),
                "Description for product 3",
                10,category, LocalDateTime.now(), LocalDateTime.now(), "Joe Doe");

         products = Set.of(product1,product2,product3);

         CartItem cartItem= new CartItem();
         cartItem.setProduct(product1);
         cartItem.setProduct(product2);
         cartItem.setProduct(product3);


        when(productRepository.findAll()).thenReturn(products);


    }

    @AfterEach
    void tearDown() {
        productRepository = null;
    }

    @Test
    @DisplayName("Find all behaviour test")
    void findAll() {
        //Set<Product> products =Set.of(product1,product2,product3);
        assertNotNull(products, "The result should not be null");
        assertEquals(3, products.size(), "The size of the product set should be 3");
        assertTrue(products.contains(product1), "The result should contain product1");
        assertTrue(products.contains(product2), "The result should contain product2");
        assertTrue(products.contains(product3), "The result should contain product3");

    }

    @Test
    @DisplayName("Find by id behaviour Test")
    void findById() {
        when(productRepository.findById(1L)).thenReturn(product1);
        Product findProduct = productRepository.findById(1L);
        assertEquals("Product 1", findProduct.getName(), "The name of the product should be 1");
        assertEquals(BigDecimal.valueOf(50.0), findProduct.getPrice(), "The price of the product should be 50.0");

    }

    @Test
    @DisplayName("Save Product behaviour")
    void save() {
    Category category= new Category("Product category", LocalDateTime.now(), LocalDateTime.now());
      Product product4 = new Product("Product 4",
                BigDecimal.valueOf(512.0),
                "Description for product 4",
                20,category, LocalDateTime.now(), LocalDateTime.now(),"Joe Doe");

        given(this.productRepository.save(product4)).willReturn(product4);
        given(this.productRepository.findById(4L)).willReturn(product4);

        this.productRepository.save(product4);

        Product savedProduct = productRepository.findById(4L);
        assertNotNull(savedProduct);

        assertEquals(product4.getName(), savedProduct.getName(), "The name of the product should be Product 4");


    }


    @Test
    void delete() {
        this.productRepository.delete(1L);
        assertNull(productRepository.findById(1L));


    }


    @Test
    @DisplayName("delete() should throw ProductNotFoundException when product does not exist")
    void delete_ShouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(1L)).thenThrow(new ProductNotFoundException());
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findById(1L);
        });

         assertEquals("Product not found", exception.getMessage());

    }

    @Test
    void getProductByName() {
        String productName = "Product 1";
        when(productRepository.getProductByName(productName)).thenReturn(Optional.of(product1));

        Optional<Product>result = productRepository.getProductByName(productName);

        assertEquals(product1.getName(),  "Product 1", "The returned product should match the expected product");

    }
}