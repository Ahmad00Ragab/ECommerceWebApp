package gov.iti.jets.cart;

import gov.iti.jets.product.Product;
import gov.iti.jets.user.User;
import jakarta.persistence.*;

@Entity
public class Cart {

    @EmbeddedId
    @AttributeOverrides( {
            @AttributeOverride(name="userId", column=@Column(name="user_id", nullable=false,insertable=false, updatable=false) ),
            @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false,insertable=false, updatable=false) ) } )

    CartKey cartId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    // Getters and Setters
    // ...
}