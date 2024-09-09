package gov.iti.jets.system.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<String> validationErrors;

    // Constructor that accepts a list of validation errors
    public ValidationException(List<String> validationErrors) {
        super("Validation failed with errors: " + String.join(", ", validationErrors)); // Optional: provide a custom message
        this.validationErrors = validationErrors;
    }

    // Getter for validation errors
    public List<String> getValidationErrors() {
        return validationErrors;
    }
}