package gov.iti.jets.category;

import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")  // Adjusted table name to lowercase
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();  // Initialized directly

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();  // Initialized directly

    public Category(String name, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}
