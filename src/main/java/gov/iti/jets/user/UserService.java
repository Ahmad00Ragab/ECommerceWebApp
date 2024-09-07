package gov.iti.jets.user;

import gov.iti.jets.category.Category;
import gov.iti.jets.product.Product;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.utils.encryption.PasswordEncryptionUtil;

import java.util.Optional;
import java.util.Set;

public class UserService {
    UserRepository userRepository;
    public UserService() {
        userRepository = new UserRepository();

    }

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException(username)));
    }

    public User save(User user) {
        user.setPassword(PasswordEncryptionUtil.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public Boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    public Boolean delete(Long userId) {
        User foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.userRepository.delete(userId);
        return true;
    }

    public User update(Long userId, User user) {
        User foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setCountry(user.getCountry());
        foundUser.setCity(user.getCity());
        foundUser.setStreet(user.getStreet());
        foundUser.setCreditLimit(user.getCreditLimit());
        foundUser.setBirthdate(user.getBirthdate());
        return userRepository.update(foundUser);
    }

    public Set<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    // Interests
    public Set<Category> getInterests(Long userId) {
        return userRepository.findInterestsByUserId(userId);
    }

    public void addInterest(Long userId, Category category) {
        userRepository.addInterestToUser(userId, category);
    }

    public void removeInterest(Long userId, Category category) {
        userRepository.removeInterestFromUser(userId, category);
    }

    // Wishlist
    public Set<Product> getWishlist(Long userId) {
        return userRepository.findWishlistByUserId(userId);
    }

    public void addToWishlist(Long userId, Product product) {
        userRepository.addProductToWishlist(userId, product);
    }

    public void removeFromWishlist(Long userId, Product product) {
        userRepository.removeProductFromWishlist(userId, product);
    }
}
