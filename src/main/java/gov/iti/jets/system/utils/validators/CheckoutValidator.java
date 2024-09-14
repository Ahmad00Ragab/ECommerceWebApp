package gov.iti.jets.system.utils.validators;

import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.User;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckoutValidator {
    private final CartService cartService;
    private final ProductService productService;

    public CheckoutValidator(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    public List<String> validateCheckout(User user, Set<CartItem> cartItems) {
        List<String> errors = new ArrayList<>();

        // Retrieve cart by id
        if (cartItems == null || cartItems.isEmpty()) {
            errors.add("Cart is empty.");
            return errors;
        }

        BigDecimal totalCost = BigDecimal.ZERO;
        boolean itemOutOfStock = false;
        // Validate stock availability and calculate total cost
        for (CartItem item : cartItems) {
            int availableStock = item.getProduct().getStock();
            if (availableStock < item.getQuantity()) {
                itemOutOfStock = true;
                errors.add("Product " + item.getProduct().getName() + " has only " + availableStock + " in stock.");
            }
            totalCost = totalCost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        if(itemOutOfStock){
            return errors;
        }

        // Validate user credit limit
        if (user.getCreditLimit().compareTo(totalCost) < 0) {
            errors.add("Insufficient credit limit.");
        }

        if (!errors.isEmpty()) {
            return errors;
        }

        return errors;
    }
}