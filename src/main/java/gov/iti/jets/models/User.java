package gov.iti.jets.models;

import gov.iti.jets.system.utils.verification.EmailStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$",
            message = "Password must contain at least one letter and one number")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email must be from gmail.com or outlook.com")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "credit_limit")
    @DecimalMin(value = "0.0", inclusive = false, message = "Credit limit must be a positive number")
    private BigDecimal creditLimit;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name="phone")
    private String phone;

    @Column(name= "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="last_updated", nullable = false)
    private LocalDate lastUpdated;

    
    // Initialize cartItems
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = new HashSet<>();

    // Initialize wishlist
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // Gives Error in code 
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders;

    @Column(name = "email_status")
    private EmailStatus verificationCode;

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
        this.cartItems = new HashSet<>();  // Initialize cartItems
        this.wishlist = new HashSet<>();  // Initialize wishlist
        this.categories = new HashSet<>();  // Initialize categories
        this.orders = new ArrayList<>();
    }

    // Constructor to initialize required fields and collections
    public User(String username, String firstName, String lastName, String email, String password,
                String country, String city, String street, BigDecimal creditCardLimit,
                LocalDate birthdate, String phone, LocalDate dateCreated,
                LocalDate lastUpdated ) {
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
        this.cartItems = new HashSet<>(); // Initialize cartItems
        this.wishlist = new HashSet<>();  // Initialize wishlist
        this.categories = new HashSet<>(); // Initialize categories
        this.orders = new ArrayList<>();

    }

    public Set<Category> getInterests() {
        return this.categories;
    }
}
