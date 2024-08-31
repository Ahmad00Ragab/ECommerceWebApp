package gov.iti.jets.cart;

import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


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
        this.cartId = new CartKey();
        this.cartId.setUserId(user.getId());
        this.cartId.setProductId(product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cart cart = (Cart) obj;
        return quantity == cart.quantity &&
                Objects.equals(cartId, cart.cartId);
    }
}