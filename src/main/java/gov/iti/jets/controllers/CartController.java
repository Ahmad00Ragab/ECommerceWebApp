package gov.iti.jets.controllers;

import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.User;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper; // Add this for JSON response handling
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {

    private CartService cartService;
    private UserService userService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
        userService = new UserService();
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("userId") == null
                || request.getSession().getAttribute("userId").toString().isEmpty())
        {
            request.setAttribute("error", "User not logged in");
            request.getRequestDispatcher("/login").include(request, response);
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "listByUser";
        switch (action) {
            case "listByUser":
                listCartItemsByUser(request, response);
                break;
            case "checkCart":
                checkCart(request, response);
                break;
            case "add":
                saveCartItem(request, response);
                break;
            case "update":
                updateCartItem(request, response); // Modified to return JSON
                break;
            case "delete":
                deleteCartItem(request, response);
                break;
            case "clear":
                clearCart(request, response);
                break;
            default:
                request.setAttribute("error", "Invalid action");
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("userId") == null
                || request.getSession().getAttribute("userId").toString().isEmpty())
        {
            // Return 401 Unauthorized if the user is not logged in
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"Please log in to add items to the cart.\"}");

            request.getRequestDispatcher("/login").include(request, response);
            return;
        }

        // Add product to cart logic...
        saveCartItem(request, response);


    }

    private void checkCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void listCartItemsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());

        // Get cart items
        Set<CartItem> cartItems = cartService.findCartByUserId(userId);
        request.setAttribute("cartItems", cartItems);

        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        request.setAttribute("totalPrice", totalPrice);

        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void saveCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        int quantity = 1; // Default to 1, you can allow dynamic quantities if needed

        // Add the product to the cart try and catch
        try {
            cartService.addProductToCart(userId, productId, quantity);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        // Check if it's an AJAX request
        String requestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWith)) {
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Item successfully added to cart!\"}");
        } else {
            // Regular redirect for non-AJAX requests
            response.sendRedirect("products");
        }
    }


    // Update cart item using AJAX
    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Long productId = Long.parseLong(request.getParameter("productId"));
        Long userId = Long.parseLong( request.getSession().getAttribute("userId").toString());

        // Update the quantity in the cart
        cartService.updateQuantity(userId, productId, quantity);

        // Get updated cart items to calculate total price again
        Set<CartItem> cartItems = cartService.findCartByUserId(userId);
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Get the updated total price for the specific product row
        CartItem updatedItem = cartService.findCartItem(userId, productId);
        BigDecimal rowTotalPrice = updatedItem.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));

        // Prepare JSON response with updated total prices
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("totalPrice", totalPrice);
        jsonResponse.put("rowTotalPrice", rowTotalPrice);

        // Set response content type to JSON
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper(); // Use Jackson to convert Java object to JSON
        mapper.writeValue(response.getWriter(), jsonResponse); // Write JSON response
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong( request.getSession().getAttribute("userId").toString());
        Long productId = Long.parseLong(request.getParameter("productId"));

        // Remove the item from the cart
        cartService.removeItem(userId, productId);

        // Get the updated cart items after deletion
        Set<CartItem> cartItems = cartService.findCartByUserId(userId);

        // Calculate the new total price after item deletion
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Prepare JSON response with the updated total price
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("totalPrice", totalPrice);

        // Send JSON response
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), jsonResponse);
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        cartService.clearCart(userId);
        response.sendRedirect("cart?action=listByUser");
    }
}