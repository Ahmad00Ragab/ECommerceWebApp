package gov.iti.jets.user;

import gov.iti.jets.category.Category;
import gov.iti.jets.order.Order;
import gov.iti.jets.product.Product;
import jakarta.persistence.*;
import gov.iti.jets.cart.CartItem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com)$",
            message = "Email must be from gmail.com or outlook.com")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name="phone")
    private String phone;

    @Column(name= "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="last_updated", nullable = false)
    private LocalDate lastUpdated;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<CartItem> cartItems;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDate.now();
    }

    public User(String username, String email, String password, LocalDate dateCreated, LocalDate lastUpdated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public User(String username, String firstName, String lastName, String email, String password,
                String country, String city, String street, BigDecimal creditCardLimit,
                LocalDate birthdate, String phone, LocalDate dateCreated,
                LocalDate lastUpdated) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.creditLimit = creditCardLimit;
        this.birthdate = birthdate;
        this.phone = phone;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.cartItems = new HashSet<>();
    }
}
