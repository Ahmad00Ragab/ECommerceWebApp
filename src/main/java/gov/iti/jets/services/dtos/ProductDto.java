package gov.iti.jets.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private Long id;
   private String name;
   private String description;
   private String imageUrl;
   private BigDecimal price;
}


