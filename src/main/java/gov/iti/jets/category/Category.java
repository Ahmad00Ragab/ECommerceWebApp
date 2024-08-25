package gov.iti.jets.category;

import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    // should be mapped by the user as many to many and inverseJoin on the cat-id
//    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
//    private Set<User> users;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category(String name, Date dateCreated, Date lastUpdated, String createdBy) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    // ...
}