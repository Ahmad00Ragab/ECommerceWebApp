package gov.iti.jets.order;

import gov.iti.jets.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Order{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
