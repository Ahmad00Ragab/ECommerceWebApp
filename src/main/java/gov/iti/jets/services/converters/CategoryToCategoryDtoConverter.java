package gov.iti.jets.services.converters;

import gov.iti.jets.models.Category;
import gov.iti.jets.services.dtos.CategoryDto;

public class CategoryToCategoryDtoConverter {
    public static CategoryDto convert(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
