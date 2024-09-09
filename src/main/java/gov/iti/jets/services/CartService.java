package gov.iti.jets.services;

import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.CartKey;
import gov.iti.jets.models.Product;
import gov.iti.jets.models.User;
import gov.iti.jets.repositories.CartRepository;
import gov.iti.jets.repositories.ProductRepository;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;

import java.util.Optional;
import java.util.Set;

public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository; // If you need to check product details
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    
    // 1. Find cart items by user ID
    public Set<CartItem> getCartByUserId(Long userId) {
        if(userRepository.existsById(userId)) {
            return cartRepository.findCartByUserId(userId);
        } else {
            throw new ObjectNotFoundException("User", userId);
        }
    }

    // 2. Add product to cart
    public void addProductToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product", productId));

        if(product.getStock() == 0) {
            throw new ObjectNotFoundException("Product", productId);
        }

        Optional<CartItem> existingCartItem = cartRepository.findById(new CartKey(userId, productId));

        if(existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem);
        }
        else{
            cartRepository.save(new CartItem(user, product, 1));
        }
    }

    // 3. Update the quantity of an item in the cart
    public void updateQuantity(Long userId, Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if(quantity == 0) {
            cartRepository.delete(new CartKey(userId, productId));
        }

        CartItem cartItem = cartRepository.findById(new CartKey(userId, productId))
                .orElseThrow(() -> new ObjectNotFoundException("Cart item", new CartKey(userId, productId)));
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    // 4. Remove an item from the cart
    public void removeItem(Long userId, Long productId) {
        CartKey cartKey = new CartKey(userId, productId);
        if (!cartRepository.exists(cartKey)) {
            throw new IllegalArgumentException("Item not found in the cart");
        }
        cartRepository.deleteCartItem(cartKey);
    }

    // 5. Clear the cart
    public void clearCart(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        cartRepository.clearCartByUserId(userId);
    }

    // 6. Get total price of cart
//    public double getTotalPrice(Long userId) {
//        if (!userRepository.existsById(userId)) {
//            throw new IllegalArgumentException("User not found");
//        }
//        return cartRepository.getTotalPrice(userId);
//    }
}
