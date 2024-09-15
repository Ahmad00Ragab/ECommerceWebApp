package gov.iti.jets.controllers;

import gov.iti.jets.services.UserService;
import gov.iti.jets.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

import java.io.IOException;




@WebServlet("/EmailCheckLoginAjax")
public class EmailCheckLoginAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        Optional<User> user = userService.findByEmail(email);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (user.isPresent()) {
            response.getWriter().write("Valid email");
        } else {
            response.getWriter().write("Invalid email");
        }
    }
}
