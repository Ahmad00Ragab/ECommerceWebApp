package gov.iti.jets.user.converter;

import gov.iti.jets.user.User;
import gov.iti.jets.user.dto.UserDto;

public class UserDtoToUserConverter {

    public static User convert(UserDto userDto, Long userId) {
        if (userDto == null) {
            return null;
        }

        User user = new User();  // Or fetch an existing user if updating
        user.setId(userId);  // Set ID if updating an existing user

        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());  // Handle password securely
        user.setPhone(userDto.phone());
        user.setCity(userDto.city());
        user.setCountry(userDto.country());
        user.setStreet(userDto.street());

        return user;
    }
}