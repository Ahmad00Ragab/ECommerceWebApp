package gov.iti.jets.controllers;

import gov.iti.jets.models.User;
import gov.iti.jets.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "verify", urlPatterns = "/verify")
public class EmailVerification extends HttpServlet {
    //private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello ");
        UserService userService = new UserService();
        String verificationCode = req.getParameter("verificationCode");
        HttpSession session = req.getSession();
        String verify= (String) session.getAttribute("verifyCode");
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUsername());
        if(verificationCode.equals(verify)){
         User newUser=  userService.save(user);
            System.out.println(newUser.getEmail());
            RequestDispatcher rd =req.getRequestDispatcher("/welcome.jsp");
            rd.forward(req, resp);
        }else {
            resp.sendRedirect("/ECommerceWebApp_war/verifyEmail.jsp");
        }

    }
}
