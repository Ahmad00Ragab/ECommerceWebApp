package gov.iti.jets.order;

import gov.iti.jets.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;

    public Order(User user, BigDecimal totalPrice, LocalDateTime dateCreated) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
    }
}
