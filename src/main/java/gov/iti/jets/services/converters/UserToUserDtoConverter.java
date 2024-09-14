package gov.iti.jets.services.converters;

import gov.iti.jets.services.dtos.UserDto;
import gov.iti.jets.models.User;

public class UserToUserDtoConverter {

    public static UserDto convert(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone(),
            user.getCity(),
            user.getCountry(),
            user.getStreet()
        );
    }
}