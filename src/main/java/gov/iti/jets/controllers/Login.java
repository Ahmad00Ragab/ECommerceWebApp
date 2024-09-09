package gov.iti.jets.controllers;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


import gov.iti.jets.models.User;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);
        if (user == null) {
            out.println("<span style='color:red;'>Login Unauthorized!</span><br>");
            request.getRequestDispatcher("index.jsp").include(request, response);
            return;
        }

        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("password", password);

        response.setContentType("text/html");

        request.getSession().setAttribute("id", user.getId());

        out.println("Login successful!<br>");

        response.sendRedirect(request.getContextPath() + "/jsp/welcome.jsp");

    }

}
