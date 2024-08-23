package gov.iti.jets.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "interests")
    private String interests;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @Column(name = "creditCardLimit")
    private Float creditCardLimit;

//    @OneToOne(mappedBy = "user", orphanRemoval = true)
//    private Cart cart;
}