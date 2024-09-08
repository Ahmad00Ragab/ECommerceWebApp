package gov.iti.jets.services;

import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.system.utils.encryption.PasswordEncryptionUtil;
import gov.iti.jets.validators.UserValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserService {
    UserRepository userRepository;
    UserValidator userValidator;
    public UserService() {
        userRepository = new UserRepository();
        userValidator = new UserValidator();
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
        // Find the user in the repository
        User foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));

        // Perform validation at the service layer
        List<String> validationErrors = createUserValidation(user);

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors); // Custom exception for validation failures
        }

        // Update the necessary fields
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setCountry(user.getCountry());
        foundUser.setCity(user.getCity());
        foundUser.setStreet(user.getStreet());
        foundUser.setCreditLimit(user.getCreditLimit());
        foundUser.setBirthdate(user.getBirthdate());
        foundUser.setPhone(user.getPhone());

        // Save the updated user
        return userRepository.update(foundUser);
    }

    public Set<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId)));
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


    // Validation
    public List<String> createUserValidation(User user){
        return userValidator.validateUserInput(user, false, true);
    }
}