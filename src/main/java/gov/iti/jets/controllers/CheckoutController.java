package gov.iti.jets.controllers;

import gov.iti.jets.models.Order;
import gov.iti.jets.services.CheckoutService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.system.utils.validators.CheckoutValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;


import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.User;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    CartService cartService = new CartService();
    UserService userService = new UserService();
    CheckoutService checkoutService = new CheckoutService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long)req.getSession().getAttribute("userId");

        if (userId == null) {
            req.setAttribute("error", "User not logged in");
            req.getRequestDispatcher("/login").include(req, resp);
            return;
        }
        checkout(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    protected void checkout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.findById((Long)req.getSession().getAttribute("userId")).get();

        Set<CartItem> cartItems = cartService.findCartByUserId(user.getId());

        // Validate checkout

        try {
            Order order = checkoutService.checkout(user, cartItems);

            // Set order in session
            req.setAttribute("order", order);

            // Redirect to home page
            req.getRequestDispatcher("/confirmation.jsp").include(req, resp);
        } catch (ValidationException e) {
            List<String> errors = e.getValidationErrors();
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/cart?action=listByUser").forward(req, resp);
            return;
        }

    }

}
