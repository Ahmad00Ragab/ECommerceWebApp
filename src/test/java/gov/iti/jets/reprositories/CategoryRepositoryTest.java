package gov.iti.jets.reprositories;
// package gov.iti.jets.category;

// import gov.iti.jets.product.Product;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.BDDMockito.given;
// import static org.mockito.Mockito.*;


// class CategoryRepositoryTest {
//     @Mock
//     private CategoryRepository categoryRepository;
//     private Category category1;
//     private Category category2;
//     private Category category3;

//     private Set<Category> categories;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         category1 = new Category("Category 1", LocalDateTime.now(), LocalDateTime.now());
//         category2 = new Category("Category 2", LocalDateTime.now(), LocalDateTime.now());
//         category3 = new Category("Category 3", LocalDateTime.now(), LocalDateTime.now());
//         categories = Set.of(category1, category2, category3);

//     }

//     @AfterEach
//     void tearDown() {
//         categoryRepository=null;
//     }

//     @Test
//     @DisplayName("Return all categories")
//     void findAll() {
//         assertNotNull(categories, "The result should not be null");
//         assertEquals(3, categories.size(), "The size of the Category set should be 3");
//         assertTrue(categories.contains(category1), "The result should contain Category 1");
//         assertTrue(categories.contains(category2), "The result should contain Category 2");
//         assertTrue(categories.contains(category3), "The result should contain Category 3");
//         assertEquals(category1.getName(), "Category 1");
//     }

//     @Test
//     @DisplayName("Find Category by id")
//     void findById() {
//         when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));
//         Category findProduct =(Category) categoryRepository.findById(1L).get();
//         assertEquals("Category 1", findProduct.getName(), "The name of the Category should be Category 1");



//     }

//     @Test
//     @DisplayName("Save a new Category")
//     void save() {
//         Category category4 = new Category("Category 4", LocalDateTime.now(), LocalDateTime.now());
//         given(this.categoryRepository.save(category4)).willReturn(category4);
//         given(this.categoryRepository.findById(4L)).willReturn(Optional.of(category4));
//         this.categoryRepository.save(category4);

//         Category savedProduct =(Category) categoryRepository.findById(4L).get();
//         assertNotNull(savedProduct);

//         assertEquals(category4.getName(), savedProduct.getName(), "The name of the Category should be Category 4");


//     }

//     @Test
//     @DisplayName("Delete category")
//     void delete() {
//         this.categoryRepository.delete(1);
//         assertNull(categoryRepository.findById(1L));

//     }


//     @Test
//     @DisplayName("Find Product by category")
//     void getProductsByCategory() {
//         Product product1 = new Product("Product 1", BigDecimal.valueOf(500), "Description for product 1", 50, category1, LocalDateTime.now(), LocalDateTime.now(), "John Doe");
//         Product product2 = new Product("Product 2", BigDecimal.valueOf(1200), "Description for product 2", 30, category1, LocalDateTime.now(), LocalDateTime.now(), "Jane Doe");

//         when(categoryRepository.getProductsByCategory(category1.getName())).thenReturn(List.of(product1,product2));

//         List<Product> result = categoryRepository.getProductsByCategory(category1.getName());


//         assertEquals(2, result.size(), "The number of products should be 2");
//         assertTrue(result.contains(product1), "The result should contain the first product");
//         assertTrue(result.contains(product2), "The result should contain the second product");

//         verify(categoryRepository, times(1)).getProductsByCategory(product1.getCategory().getName());


//     }
// }