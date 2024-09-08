package gov.iti.jets.validators;

import gov.iti.jets.models.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    // Main method to validate user attributes based on required fields for each context
    public List<String> validateUserInput(User user, boolean isAddressRequired, boolean isRegistration) {
        List<String> errors = new ArrayList<>();

        // Validate username
        if (isNullOrEmpty(user.getUsername()) || !isValidUsername(user.getUsername())) {
            errors.add("Invalid username: must be at least 3 characters long.");
        }

        // Validate email (always required)
        if (isNullOrEmpty(user.getEmail()) || !validateEmail(user.getEmail())) {
            errors.add("Invalid email: must follow correct format and belong to allowed domains.");
        }

        // Validate phone (only if present, not mandatory)
        if (!isNullOrEmpty(user.getPhone()) && !validatePhone(user.getPhone())) {
            errors.add("Invalid phone number: must be between 10-15 digits.");
        }

        // Validate password (only for registration)
        if (isRegistration && (isNullOrEmpty(user.getPassword()) || !validatePassword(user.getPassword()))) {
            errors.add("Invalid password: must be at least 6 characters long.");
        }

        // Validate credit limit (if applicable)
        if (user.getCreditLimit() != null && !validateCreditLimit(user.getCreditLimit())) {
            errors.add("Invalid credit limit: must be a positive value.");
        }

        // Validate address (optional for profile update, mandatory for orders)
        if (isAddressRequired && !validateAddress(user)) {
            errors.add("Invalid address: country, city, and street are required.");
        }

        return errors;
    }

    // Validation for address
    private boolean validateAddress(User user) {
        return !isNullOrEmpty(user.getCountry()) &&
                !isNullOrEmpty(user.getCity()) &&
                !isNullOrEmpty(user.getStreet());
    }

    // Username validation
    private boolean isValidUsername(String username) {
        return username.length() >= 3;
    }

    // Email validation
    private boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    // Phone validation
    private boolean validatePhone(String phone) {
        return phone.matches("^\\+?[0-9]{10,15}$");
    }

    // Password validation (for registration)
    private boolean validatePassword(String password) {
        return password.length() >= 6;  // Adjust complexity requirements if needed
    }

    // Credit limit validation
    private boolean validateCreditLimit(BigDecimal creditLimit) {
        return creditLimit.compareTo(BigDecimal.ZERO) > 0;
    }

    // Utility method for checking null or empty strings
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}