package gov.iti.jets.controllers;

import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "validate", urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String field = req.getParameter("field");
        String value = req.getParameter("value");

        boolean isValid = true;

        if ("username".equals(field)) {
            isValid = !userService.existsByUsername(value);
        } else if ("email".equals(field)) {
            isValid = !userService.existsByEmail(value);
        }

        resp.setContentType("application/json");
        resp.getWriter().write("{\"valid\": " + isValid + "}");
    }
}
