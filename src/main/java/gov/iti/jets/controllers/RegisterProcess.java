package gov.iti.jets.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.User;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterProcess extends HttpServlet {

    private UserService userService = new UserService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAllCategories());
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
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

        User user = new User(username, firstName, lastName, email, password, country, city,
                street, new BigDecimal(creditLimit), dateOfBirth, phone, LocalDate.now(), LocalDate.now());

        List<String> validationErrors = userService.validateUserInput(user);

        if (userService.existsByUsername(username)) {
            validationErrors.add("Username already exists");
        }

        if (userService.existsByEmail(email)) {
            validationErrors.add("Email already exists");
        }

        String[] selectedCategoryIds = req.getParameterValues("categories");
        Set<Category> userCategories = new HashSet<>();

        if (selectedCategoryIds != null) {
            for (String categoryId : selectedCategoryIds) {
                Category category = categoryService.findCategoryById(Long.parseLong(categoryId));
                if (category != null) {
                    userCategories.add(category);
                }
            }
        }

        user.setCategories(userCategories);

        if (!validationErrors.isEmpty()) {
            if (isAjaxRequest(req)) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(validationErrors);
                resp.getWriter().write(jsonResponse);
            } else {
                req.setAttribute("validationErrors", validationErrors);
                req.setAttribute("categories", categoryService.findAllCategories());
                req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            }
            return;
        }

        userService.save(user);
        req.getSession().setAttribute("userId", user.getId());
        // resp.sendRedirect("/ECommerceWebApp/index.jsp");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");

    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
