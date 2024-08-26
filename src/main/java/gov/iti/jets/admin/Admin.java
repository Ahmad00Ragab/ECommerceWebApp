package gov.iti.jets.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name ="user_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name= "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="last_update", nullable = false)
    private LocalDate lastUpdate;

    @Column(name = "created_by")
    private String createdBy;

    public Admin(String name, String email, String password, LocalDate dateCreated, LocalDate lastUpdate, String createdBy) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.lastUpdate = lastUpdate;
        this.createdBy = createdBy;
    }
}
