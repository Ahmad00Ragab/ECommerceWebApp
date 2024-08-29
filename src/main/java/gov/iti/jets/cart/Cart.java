package gov.iti.jets.cart;

import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart {

    @EmbeddedId
    @AttributeOverrides( {
            @AttributeOverride(name="userId", column=@Column(name="user_id")),
            @AttributeOverride(name="productId", column=@Column(name="product_id"))} )
    private CartKey cartId;

    @Column(nullable = false)
    private int quantity;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;


    public Cart(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}