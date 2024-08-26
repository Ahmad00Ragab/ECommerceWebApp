package gov.iti.jets.user;


import gov.iti.jets.category.Category;
import gov.iti.jets.common.UserRole;
import gov.iti.jets.order.Order;
import gov.iti.jets.product.Product;
import jakarta.persistence.*;

import gov.iti.jets.cart.Cart;
import gov.iti.jets.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
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
    private String userCity;

    @Column(name = "street")
    private String userStreet;

    @Column(name = "credit_limit")
    private Float creditCardLimit;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "job")
    private String job;

    @Column(name="phone")
    private String userPhone;

    @Column(name= "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="last_update", nullable = false)
    private LocalDate lastUpdate;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> cart;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist;

    @ManyToMany
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;



    public User(String username, String firstName, String lastName,
                String email, String password,
                String country, String userCity,
                String userStreet, Float creditCardLimit,
                LocalDate birthdate, String job, String userPhone,
                LocalDate dateCreated, LocalDate lastUpdate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.userCity = userCity;
        this.userStreet = userStreet;
        this.creditCardLimit = creditCardLimit;
        this.birthdate = birthdate;
        this.job = job;
        this.userPhone = userPhone;
        this.dateCreated = dateCreated;
        this.lastUpdate = lastUpdate;
        this.cart = new HashSet<>();
        this.categories = new HashSet<>();
        this.orders = new ArrayList<>();
        this.wishlist= new HashSet<>();
    }
}