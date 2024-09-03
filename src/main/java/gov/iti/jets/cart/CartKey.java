package gov.iti.jets.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
import lombok.*;
>>>>>>> master

import java.io.Serializable;

@Embeddable
@Setter
@Getter
<<<<<<< HEAD
class CartKey implements Serializable {
  
    @Column(name = "user_id", nullable = false)
    Long userId;
  
    @Column(name = "product_id", nullable = false)
    Long productId;
  
    // standard constructors, getters, and setters
    CartKey(Long userId, Long productId ) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartKey() {}
    // hashcode and equals implementation
=======
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class CartKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "product_id", nullable = false)
    Long productId;


>>>>>>> master
}