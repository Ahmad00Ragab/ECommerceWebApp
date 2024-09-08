package gov.iti.jets.product;

import gov.iti.jets.cart.CartItem;
import gov.iti.jets.category.Category;
import lombok.*;
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

    @Column(nullable = false, unique = true)
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

    @OneToMany(mappedBy = "product")
    private Set<CartItem> carts;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name="product_image")
    private String imageUrl;

    public Product(String name, BigDecimal price, String description, int stock, Category category, LocalDateTime dateCreated, LocalDateTime lastUpdated, String createdBy, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        cart=new HashSet<>();
        this.createdBy=createdBy;
        this.imageUrl=imageUrl;
    }

    public Product(String name, BigDecimal price, int stock, Category category, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}