package gov.iti.jets.controllers;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Set;

import gov.iti.jets.services.AdminService;
import gov.iti.jets.services.CartService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import gov.iti.jets.models.*;


@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    private AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Admin> admin = adminService.checkLogin(email, password);

        try {
            System.out.println(admin.get().getName());
            log(admin.get().getName());
            request.getSession().setAttribute("id", admin.get().getId());
            // Redirect to the cart page after successful login
            response.sendRedirect(request.getContextPath() + "/ProductController");
        } catch (Exception e) {
            // Set error message as an attribute and forward it to the login JSP page
            request.setAttribute("errorMessage", "Login Unauthorized!");
            request.getRequestDispatcher("WEB-INF/views/admin/admin-login.jsp").forward(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); 
    }
}
