package gov.iti.jets.controllers;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Set;


import gov.iti.jets.models.CartItem;
import gov.iti.jets.models.User;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class Login extends HttpServlet {

    private UserService userService = new UserService();
    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe"); // Get the checkbox value


        try {
            Optional<User> user = userService.login(email, password);
            System.out.println(user.get().getUsername());
            log(user.get().getUsername());
            request.getSession().setAttribute("userId", user.get().getId());

//            // Set cookie with user id
//            String cookieValue = String.valueOf(user.get().getId());
//            Cookie cookie = new Cookie("userId", cookieValue);
//
//            // Set cookie lifespan
//            if ("on".equals(rememberMe)) {
//                cookie.setMaxAge(60 * 60 * 24 * 7);
//            } else {
//                cookie.setMaxAge(-1);
//            }
//
//            cookie.setPath("/");
//            cookie.setHttpOnly(true);
//            cookie.setSecure(true);
//
//            response.addCookie(cookie);

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            // Set error message as an attribute and forward it to the login JSP page
            request.setAttribute("errorMessage", "Login Unauthorized!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
