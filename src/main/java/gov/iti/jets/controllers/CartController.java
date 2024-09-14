package gov.iti.jets.controllers;

import gov.iti.jets.models.CartItem;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebServlet("/cart")
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

        Long userId = (Long)request.getSession().getAttribute("userId");

        if (userId == null) {
            request.setAttribute("error", "User not logged in");
            request.getRequestDispatcher("/assets/login").include(request, response);
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";
        System.out.println(action);
        switch (action) {
            case "delete":
                deleteCartItem(request, response);
                break;
            case "add":
                saveCartItem(request, response);
                break;
            case "update":
                updateCartItem(request, response);
                break;
            case "clear":
                clearCart(request, response);
                break;
            case "listByUser":
                listCartItemsByUser(request, response);
                break;
            default:
                request.setAttribute("error", "Invalid action");
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private void listCartItemsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Long userId = (Long)request.getSession().getAttribute("userId");
        Long userId = Long.parseLong(request.getParameter("userId"));
        Set<CartItem> cartItems = cartService.findCartByUserId(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/cartItemList.jsp").forward(request, response);
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        Long productId = Long.parseLong(request.getParameter("productId"));
        cartService.removeItem(userId, productId);
        response.sendRedirect("cart?action=list");
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        cartService.clearCart(userId);
        response.sendRedirect("cart?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("save")) {
            saveCartItem(request, response);
        } else if (action.equals("update")) {
            updateCartItem(request, response);
        }
    }

    private void saveCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        Long userId = (Long)request.getSession().getAttribute("userId");
        int quantity = 1;

        cartService.addProductToCart(userId, productId, quantity);


        // send message to client (product added)
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Long productId = Long.parseLong(request.getParameter("productId"));
        //Long userId = (Long)request.getSession().getAttribute("userId");

        Long userId = Long.parseLong(request.getParameter("userId"));

        System.out.println("Action: " + request.getParameter("action"));
        System.out.println("UserId: " + request.getParameter("userId"));
        System.out.println("ProductId: " + request.getParameter("productId"));
        System.out.println("Quantity: " + request.getParameter("quantity"));

        cartService.updateQuantity(userId, productId, quantity);
        response.sendRedirect("cart?action=list");
    }
}