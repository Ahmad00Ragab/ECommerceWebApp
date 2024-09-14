package gov.iti.jets.services.converters;

import gov.iti.jets.services.dtos.UserDto;
import gov.iti.jets.models.User;

public class UserDtoToUserConverter {

    public static User convert(UserDto userDto) {

        if (userDto == null) {
            return null;
        }

        User user = new User();  // Or fetch an existing user if updating

        // Set ID if updating an existing user
        user.setId(userDto.id());
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setCity(userDto.city());
        user.setCountry(userDto.country());
        user.setStreet(userDto.street());

        return user;
    }
}