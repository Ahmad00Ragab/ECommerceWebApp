package gov.iti.jets.user.converter;

import gov.iti.jets.user.User;
import gov.iti.jets.user.dto.UserDto;

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