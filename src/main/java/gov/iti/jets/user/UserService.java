package gov.iti.jets.user;

import java.util.Set;

public class UserService {
    UserRepository userRepository;
    public UserService() {
        userRepository = new UserRepository();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public Set<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }


}
