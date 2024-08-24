package gov.iti.jets.user;

public class UserService {
    UserRepository userRepository;
    public UserService() {
        userRepository = new UserRepository();
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
