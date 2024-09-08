package gov.iti.jets.product;

import java.math.BigDecimal;

public record ProductDto (
     Long id,
     String name,
     String description,
     String imageUrl,
     BigDecimal price
){
}
