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
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterProcess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         registrationProcess(req, resp);
    }


    private void registrationProcess (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        User user =
                new User(username, firstName, lastName, email,
                        password, country, city, street, new BigDecimal(creditLimit), dateOfBirth, phone
                        , LocalDate.now(), LocalDate.now());

        String verificationCode = EmailSender.getRandom();

        HttpSession session = req.getSession();
        session.setAttribute("verifyCode", verificationCode);
        session.setAttribute("user", user);


        try {
            EmailSender.sendVerificationCode(email,verificationCode);
            RequestDispatcher rd =req.getRequestDispatcher("/verifyEmail.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            throw new CannotSendMessageException();
        }

    }
}
