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

    public CartService() {
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
        this.userRepository = new UserRepository();
    }

    
    // 1. Find cart items by user ID
    public Set<CartItem> findCartByUserId(Long userId) {
        if(userRepository.existsById(userId)) {
            return cartRepository.findCartByUserId(userId);
        } else {
            throw new ObjectNotFoundException("User", userId);
        }
    }

    // 2. Add product to cart
    public void addProductToCart(Long userId, Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product", productId));

        if(product.getStock() == 0) {
            throw new ObjectNotFoundException("Product", productId);
        }

        Optional<CartItem> existingCartItem = cartRepository.findCartByUserId(userId).stream().filter(cartItem -> cartItem.getProductId().equals(productId)).findFirst();

        if(existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepository.update(cartItem);
        }
        else{
            cartRepository.addCartItem(userId, productId, quantity);
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
        cartRepository.update(cartItem);
    }

    // 4. Remove an item from the cart
    public void removeItem(Long userId, Long productId) {
        Set<CartItem> cart = cartRepository.findCartByUserId(userId);
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                cartRepository.deleteCartItem(new CartKey(userId, productId));
                break;
            }
        }
    }

    // 5. Clear the cart
    public void clearCart(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        cartRepository.clearCartByUserId(userId);
    }

    public CartItem findCartItem(Long userId, Long productId) {
        return cartRepository.findById(new CartKey(userId, productId))
                .orElseThrow(() -> new ObjectNotFoundException("Cart item", new CartKey(userId, productId)));
    }

    // 6. Get total price of cart
//    public double getTotalPrice(Long userId) {
//        if (!userRepository.existsById(userId)) {
//            throw new IllegalArgumentException("User not found");
//        }
//        return cartRepository.getTotalPrice(userId);
//    }
}
