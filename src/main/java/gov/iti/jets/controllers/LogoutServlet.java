package gov.iti.jets.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Invalidate the current session, if it exists
        HttpSession session = request.getSession(false); // false means don't create a new session
        if (session != null) {
            session.invalidate(); // Ends the session
        }

        // Redirect to the login page
        response.sendRedirect(request.getContextPath() + "/AdminLogin"); // Redirect to the login page
    }
}
