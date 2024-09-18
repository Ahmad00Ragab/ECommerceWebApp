package gov.iti.jets.services;

import gov.iti.jets.models.*;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.system.persistence.CreateEntityManagerFactory;
import gov.iti.jets.system.persistence.EntityManagerUtil;
import gov.iti.jets.system.utils.validators.CheckoutValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckoutService {
  private final UserService userService;
  private final CartService cartService;
  private final ProductService productService;
  private final CheckoutValidator checkoutValidator;
  private  final OrderService orderService;

    protected EntityManagerFactory emf = CreateEntityManagerFactory.getInstance();


    public CheckoutService() {
        userService = new UserService();
        cartService = new CartService();
        productService = new ProductService();
        checkoutValidator = new CheckoutValidator(cartService, productService);
        orderService = new OrderService();
    }


    //1- press on checkout button go to the checkout servlet and get cart by id from database
    //
    //2- check all items for stock availability
    //
    //3- check user credit limit
    //
    //    transaction begin
    //4- if done update products in database clear cart and
    //    transaction end

    public Order checkout(User user, Set<CartItem> cartItems) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        // Begin transaction for checkout process
        List<String> errors = new ArrayList<>();
        try {
            errors = checkoutValidator.validateCheckout(user, cartItems);

            if(errors.size() > 0) {
                throw new ValidationException(errors);
            }
            // Update stock for each product
            BigDecimal totalCost = BigDecimal.ZERO;

            Order order = new Order(user, totalCost, LocalDateTime.now());

            Set<OrderItem> orderItems = new HashSet<>();


            for (CartItem item : cartItems) {
                Product product = item.getProduct();
                int availableStock = product.getStock();
                int newStock = availableStock - item.getQuantity();
                product.setStock(newStock); // Update stock
                totalCost = totalCost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

                // to display in orders
                OrderItem orderItem = new OrderItem(order, product, item.getQuantity(), item.getProduct().getPrice());
                orderItems.add(orderItem);

                productService.updateProduct(product);
            }

            user.setCreditLimit(user.getCreditLimit().subtract(totalCost));
            userService.update(user);


            order.setTotalPrice(totalCost);
            // set order items
            order.setOrderItems(orderItems);

            // saving order in the database
            orderService.createOrder(order);

            //Clear cart after successful stock update
            cartService.clearCart(user.getId());

            return order;

        } catch (Exception e) {
            //errors.add("Error during checkout: " + e.getMessage());
            throw new ValidationException(errors);
        }
    }

}
