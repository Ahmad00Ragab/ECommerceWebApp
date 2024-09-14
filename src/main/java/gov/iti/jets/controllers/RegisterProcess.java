package gov.iti.jets.controllers;

import gov.iti.jets.models.User;
import gov.iti.jets.services.UserService;
import gov.iti.jets.system.exceptions.CannotSendMessageException;
import gov.iti.jets.system.utils.verification.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "register", urlPatterns = "/assets/register")
public class RegisterProcess extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/assets/signup.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         registrationProcess(req, resp);
    }


    private void registrationProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("birthdate"));
        String creditLimit = req.getParameter("creditLimit");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String phone = req.getParameter("phone");

        boolean uniqueUsername = userService.existsByUsername(username);
        boolean uniqueEmail = userService.existsByEmail(email);

        // List to hold validation errors
        List<String> validationErrors = new ArrayList<>();

        // Check for unique username and email
        if (uniqueUsername) {
            req.setAttribute("usernameError", "Username already exists.");
            validationErrors.add("Username already exists.");
        }

        if (uniqueEmail) {
            req.setAttribute("emailError", "Email already exists.");
            validationErrors.add("Email already exists.");
        }

        // Validate the rest of the user input
        User user = new User(username, firstName, lastName, email, password, country, city,
                street, new BigDecimal(creditLimit), dateOfBirth, phone, LocalDate.now(), LocalDate.now());
        List<String> inputValidationErrors = userService.validateUserInput(user);

        // Add input validation errors to the list
        validationErrors.addAll(inputValidationErrors);

        if (!validationErrors.isEmpty()) {
            // Set validation errors in the request attribute for JSP
            req.setAttribute("validationErrors", validationErrors);

            // Forward back to the registration page with errors
            RequestDispatcher dispatcher = req.getRequestDispatcher("/assets/signup.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        // Proceed with saving the user
        try {
            userService.save(user);
            req.getSession().setAttribute("id", user.getId());
            resp.sendRedirect("/ECommerceWebApp/index.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        String verificationCode = EmailSender.getRandom();
//
//        HttpSession session = req.getSession();
//        session.setAttribute("verifyCode", verificationCode);
//        session.setAttribute("user", user);
//
//
//        try {
//            EmailSender.sendVerificationCode(email,verificationCode);
//            RequestDispatcher rd =req.getRequestDispatcher("/verifyEmail.jsp");
//            rd.forward(req, resp);
//
//        } catch (Exception e) {
//            throw new CannotSendMessageException();
//        }

    }
}
