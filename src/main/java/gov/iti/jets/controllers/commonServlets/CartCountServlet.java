package gov.iti.jets.controllers.commonServlets;

import gov.iti.jets.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/getCartCount")
public class CartCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int cartCount = 0;

        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int cartNumber = user.getCartItems().size(); // Assuming cart has a method to get item count
            }
        }

        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(cartCount));
    }
}