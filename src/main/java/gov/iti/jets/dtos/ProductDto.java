package gov.iti.jets.dtos;

import java.math.BigDecimal;

public record ProductDto (
     Long id,
     String name,
     String description,
     String imageUrl,
     BigDecimal price
){
}
