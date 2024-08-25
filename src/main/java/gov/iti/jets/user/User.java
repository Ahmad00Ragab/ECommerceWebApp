package gov.iti.jets.user;

import gov.iti.jets.cart.Cart;
import gov.iti.jets.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com)$",
            message = "Email must be from gmail.com or outlook.com")
    private String email;

    @Column(nullable = false)
    private String password;

    private String country;
    private String city;
    private String street;
    private Double creditLimit;
    private Date birthdate;
    private String phone;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    // Relationships
//    @OneToMany(mappedBy = "user")
//    private Set<UserInterest> interests;

    @OneToMany(mappedBy = "user")
    private Set<Cart> cart;

//    @OneToMany(mappedBy = "user")
//    private Set<Wishlist> wishlist;

//    @OneToMany(mappedBy = "user")
//    private Set<Order> orders;

    // Getters and Setters
    // ...
}