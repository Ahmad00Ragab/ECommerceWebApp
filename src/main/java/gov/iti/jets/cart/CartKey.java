package gov.iti.jets.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class CartKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "product_id", nullable = false)
    Long productId;


}