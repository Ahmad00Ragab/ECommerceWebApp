package gov.iti.jets.models;

import lombok.*;
import gov.iti.jets.repositories.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private int stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<CartItem> cart;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name ="image_url")
    private String imageUrl;

    public Product(String name, BigDecimal price, String description, int stock, Category category, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        cart=new HashSet<>();
    }

    public Product(String name, BigDecimal price, int stock, Category category, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        cart=new HashSet<>();
    }

    
     // Constructor that accepts categoryId as a String
     public Product(String name, BigDecimal price, int stock, String categoryId) {
        this.name = name;
        this.price = price;  // Convert double to BigDecimal
        this.stock = stock;
        this.dateCreated = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.cart = new HashSet<>();

        // Fetch Category from repository using the categoryId
        Long categoryIdLong = Long.parseLong(categoryId); 
        this.category = (new CategoryRepository(Category.class)).findById(categoryIdLong)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));
    } 
    

     /* Constructor that accepts categoryId as a String */
     public Product(String name, BigDecimal price, int stock, Category category) {
        this.name        = name;
        this.price       = price;  
        this.stock       = stock;
        this.dateCreated = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.category    = category;
        this.cart = new HashSet<>();
    }




}