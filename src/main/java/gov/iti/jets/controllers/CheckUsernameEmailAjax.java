package gov.iti.jets.controllers;

import java.io.IOException;
import java.util.Optional;

import gov.iti.jets.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.*;


@WebServlet("/CheckUsernameEmailAjax")
public class CheckUsernameEmailAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String field = request.getParameter("field"); // 'username' or 'email'
        String value = request.getParameter("value"); // value of username or email

        boolean isUnique = false;

        if ("username".equals(field)) {
            isUnique = !userService.existsByUsername(value);
        } else if ("email".equals(field)) {
            isUnique = !userService.existsByEmail(value);
        }

        response.setContentType("text/plain");
        if (isUnique) {
            response.getWriter().println("valid " + field);
        } else {
            response.getWriter().println("invalid " + field);
        }
    }
}
