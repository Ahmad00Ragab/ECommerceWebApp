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

    @Column(name ="shoe_color", nullable = true)
    private String shoeColor;

    @Column(name ="shoe_size", nullable = true)
    private String shoeSize;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<CartItem> cart;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false )
    private Category category;

    @Column(name = "date_created", nullable = true)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = true)
    private LocalDateTime lastUpdated;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name= "product_image")
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

    public Product(String imageUrl, String shoeSize, String shoeColor, int stock, String description, BigDecimal price, String name) {
        this.imageUrl = imageUrl;
        this.shoeSize = shoeSize;
        this.shoeColor = shoeColor;
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.name = name;
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


    public Product(String name, BigDecimal price, int stock, String categoryId) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.dateCreated = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.cart = new HashSet<>();


        Long categoryIdLong = Long.parseLong(categoryId);
        this.category = new CategoryRepository().findById(categoryIdLong)
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