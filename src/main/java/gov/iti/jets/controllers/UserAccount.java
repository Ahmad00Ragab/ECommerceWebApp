package gov.iti.jets.controllers;

import gov.iti.jets.models.Category;
import gov.iti.jets.models.Order;
import gov.iti.jets.models.User;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.OrderService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.converters.CategoryToCategoryDtoConverter;
import gov.iti.jets.services.dtos.CategoryDto;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(value = "/userAcc")
public class UserAccount extends HttpServlet {

    UserService userService = new UserService();
    CategoryService categoryService = new CategoryService();
    OrderController orderController = new OrderController(new OrderService());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(req.getSession().getAttribute("userId") == null
                || req.getSession().getAttribute("userId").toString().isEmpty())
        {
            req.setAttribute("error", "User not logged in");
            req.getRequestDispatcher("/login").include(req, resp);
            return;
        }
        showUpdateForm(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            switch (action) {
                case "update":
                    updateUser(req, resp);
                    break;
                case "changePassword":
                    changePassword(req, resp);
                    break;
                case "viewOrders":
                    viewOrders(req, resp);
                    break;
                default:
                    req.setAttribute("error", "Invalid action");
                    req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void viewOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.parseLong(req.getSession().getAttribute("userId").toString());
        Optional<User> userOpt = userService.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Order> orders = user.getOrders(); // Retrieve the user's orders
            req.setAttribute("orders", orders);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/userOrders.jsp").forward(req, resp); // Forward to order
            // history page
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private Optional<User> findUserById(HttpServletRequest req) {
        if(req.getSession().getAttribute("userId") == null)
        {
            return null;
        }
        Long userId = Long.parseLong(req.getSession().getAttribute("userId").toString());

        return userService.findById(userId);
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> userOpt = findUserById(req);
        if (userOpt != null) {
            req.setAttribute("user", userOpt.get());

            // Fetch all categories (available interests)
            Set<Category> categories = categoryService.findAllCategories();

            // Fetch user's selected interests
            Set<Category> userInterests = userService.getInterests(userOpt.get().getId());

            // Get only the IDs of the user's selected interests
            Set<Long> selectedInterestIds = userInterests.stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());

            // Set both attributes in the request
            req.setAttribute("categories", categories);
            req.setAttribute("selectedInterestIds", selectedInterestIds);  // Pass only IDs
            req.getRequestDispatcher("/My-Account.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/login").include(req, resp);
        }
    }


    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getSession().getAttribute("userId").toString());
        Optional<User> existingUserOpt = userService.findById(userId);
        System.out.println("here in update user");
        if (!existingUserOpt.isPresent()) {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }

        User existingUser = existingUserOpt.get();
        // Update only the fields that are provided
        existingUser.setFirstName(req.getParameter("firstname"));
        existingUser.setLastName(req.getParameter("lastname"));
        existingUser.setPhone(req.getParameter("phone"));
        existingUser.setBirthdate( LocalDate.parse(req.getParameter("birthdate")));
        existingUser.setCity(req.getParameter("city"));
        existingUser.setCountry(req.getParameter("country"));
        existingUser.setStreet(req.getParameter("street"));

        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditlimit"));
        // Set the credit limit in the existingUser object
        existingUser.setCreditLimit(creditLimit);

        // Get selected categories (interests) from the request
        String[] selectedCategoryIds = req.getParameterValues("categories");

        //find categories by Id
        Set<Category> categories = categoryService.findAllCategories();

        // get categories that has the same id as selectedCategoryIds
        Set<Category> selectedCategories = categories.stream()
                .filter(category -> Arrays.asList(selectedCategoryIds).contains(String.valueOf(category.getId())))
                .collect(Collectors.toSet());

        existingUser.setCategories(selectedCategories);

        // Perform validation
        System.out.println("just before validation");

        try {
            // Call the service to update the user
            User updatedUser =  userService.update(userId, existingUser);

            req.setAttribute("successMessage", "User updated successfully.");
            showUpdateForm(req, resp);
        } catch (ValidationException e) {
            // Set validation errors and user data in request
            req.setAttribute("errors", e.getValidationErrors());
            req.setAttribute("user", existingUser);
            // Forward to the update form
            showUpdateForm(req, resp);
        } catch (ObjectNotFoundException e) {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> existingUserOpt = findUserById(req);
        System.out.println("here in update Password");
        if (!existingUserOpt.isPresent()) {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }

        User existingUser = existingUserOpt.get();
        try {
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");

            // Perform validation
            System.out.println("just before validation");
            // Call the service to update the user
            userService.changePassword(existingUser, oldPassword, newPassword);
            req.setAttribute("successMessage", "User updated successfully.");
            showUpdateForm(req, resp);
        } catch (ValidationException e) {
            // Set validation errors and user data in request
            req.setAttribute("errors", e.getValidationErrors());
            // Forward to the update form
            showUpdateForm(req, resp);
        }

    }

}
