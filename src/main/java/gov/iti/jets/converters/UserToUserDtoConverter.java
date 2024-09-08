package gov.iti.jets.converters;

import gov.iti.jets.dtos.UserDto;
import gov.iti.jets.models.User;

public class UserToUserDtoConverter {

    public static UserDto convert(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),  // Note: Ensure you handle sensitive data securely
            user.getPhone(),
            user.getCity(),
            user.getCountry(),
            user.getStreet()
        );
    }
}