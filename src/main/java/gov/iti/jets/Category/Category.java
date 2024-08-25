package gov.iti.jets.Category;

import gov.iti.jets.Product.Product;
import gov.iti.jets.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    // Getters and Setters
    // ...
}