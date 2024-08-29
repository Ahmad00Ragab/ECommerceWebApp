package gov.iti.jets.product;

import gov.iti.jets.cart.Cart;
import gov.iti.jets.category.Category;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Cart> cart;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    public Product(String name, double price, String description, int stock, Category category, Date dateCreated, Date lastUpdated, String createdBy) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.createdBy = createdBy;
        cart=new HashSet<>();
    }
}