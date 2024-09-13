package gov.iti.jets.dtos;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private Long id;
   private String name;
   private String description;
   private String imageUrl;
   private BigDecimal price;
}


