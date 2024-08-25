package gov.iti.jets.order;

import gov.iti.jets.user.User;
import jakarta.persistence.*;

@Entity
public class Order{

    @Id
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
