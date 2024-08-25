package gov.iti.jets.user;


import gov.iti.jets.common.UserRole;
import jakarta.persistence.*;
import gov.iti.jets.cart.Cart;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "street")
    private String userStreet;

    @Column(name = "city")
    private String userCity;



    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com)$",
            message = "Email must be from gmail.com or outlook.com")
    private String email;

    @Column(name = "interests")
    private String interests;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "job")
    private String job;

    @Column(name = "credit_card")
    private Float creditCardLimit;

    @OneToOne(mappedBy = "user")
    private Cart cart;




}