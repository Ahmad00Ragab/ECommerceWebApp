package gov.iti.jets.controllers;

import gov.iti.jets.services.converters.UserToUserDtoConverter;
import gov.iti.jets.models.User;
import gov.iti.jets.models.Order;
import gov.iti.jets.services.UserService;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.services.converters.UserDtoToUserConverter;
import gov.iti.jets.services.dtos.UserDto;
import gov.iti.jets.services.dtos.UserOrderDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;


@WebServlet(name = "UserController", value = "/user")
public class UserController extends HttpServlet {

    UserService userService = new UserService();
    private UserToUserDtoConverter userToUserDtoConverter = new UserToUserDtoConverter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("here in get");
        log("here in get");

        // Validate session for admin or user
        HttpSession session = req.getSession(false); // Get existing session, but don't create a new one

        if (session == null || (session.getAttribute("id") == null && session.getAttribute("userId") == null)) {
            // Neither admin nor user is logged in
            req.setAttribute("errorMessage", "Please log in to access this page.");

            // Check if the request is for an admin or a user and redirect accordingly
            if (session.getAttribute("id") == null) {
                req.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            return;
        }

        switch (action) {
            case "updateForm":
                showUpdateForm(req, resp);
                break;
            case "confirmDelete":
                deleteUser(req, resp);
                break;
            case "view":
                viewUser(req, resp);
                break;
            case "list":
                listUsers(req, resp);
                break;
            case "viewOrderHistory": // New action to view order history
                viewOrderHistory(req, resp);
                break;
        }
    }

    private void viewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> userOpt = findUserById(req);
        if (userOpt.isPresent()) {
            req.setAttribute("user", userOpt.get());
            req.getRequestDispatcher("WEB-INF/views/user/view.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> users;
        users = userService.findAll();

        req.setAttribute("users", users);

        System.out.println("Users: " + users);

        String error = (String) req.getAttribute("error"); // Optional error
        req.setAttribute("error", error);

        req.getRequestDispatcher("WEB-INF/views/user/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        // Validate session for admin or user
        HttpSession session = req.getSession(false); // Get existing session, but don't create a new one

        if (session == null || (session.getAttribute("id") == null && session.getAttribute("userId") == null)) {
            // Neither admin nor user is logged in
            req.setAttribute("errorMessage", "Please log in to access this page.");

            // Check if the request is for an admin or a user and redirect accordingly
            if (session.getAttribute("id") == null) {
                req.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            return;
        }

        String action = req.getParameter("action");

        try {
            switch (action) {
                case "update":
                    updateUser(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    req.setAttribute("error", "Invalid action");
                    req.getRequestDispatcher("../error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private Optional<User> findUserById(HttpServletRequest req) {
        Long userId = Long.parseLong(req.getParameter("userId"));
        return userService.findById(userId);
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> userOpt = findUserById(req);
        if (userOpt.isPresent()) {
            req.setAttribute("user", userOpt.get());
            req.getRequestDispatcher("WEB-INF/views/user/update.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        Optional<User> existingUserOpt = userService.findById(userId);
        System.out.println("here in update user");
        if (!existingUserOpt.isPresent()) {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }

        User existingUser = existingUserOpt.get();
        // Update only the fields that are provided
        existingUser.setUsername(req.getParameter("username"));
        existingUser.setPhone(req.getParameter("phone"));
        existingUser.setCity(req.getParameter("city"));
        existingUser.setCountry(req.getParameter("country"));
        existingUser.setStreet(req.getParameter("street"));

        /* Update the Rest of user Attributes : Haroun */
        // Convert the string to LocalDate
        LocalDate birthdate = LocalDate.parse(req.getParameter("birthdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        existingUser.setBirthdate(birthdate);
        existingUser.setEmail(req.getParameter("email"));
        existingUser.setCreditLimit(new BigDecimal(req.getParameter("creditLimit")));

        // Perform validation
        System.out.println("just before validation");

        try {
            // Call the service to update the user
            userService.update(userId, existingUser);
            req.setAttribute("successMessage", "User updated successfully.");
            req.setAttribute("user", existingUser);
            req.getRequestDispatcher("WEB-INF/views/user/update.jsp").forward(req, resp);

            /*
             * Commented : After Updating, Display the message "User updated Successfuly" in
             * the same page, no need to go success page
             */
            // req.getRequestDispatcher("/jsp/user/success.jsp").forward(req, resp);
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

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        System.out.println("here in delete user");
        try {
            Optional<User> userOpt = userService.findById(userId);
            System.out.println("find user");

            if (userOpt.isPresent()) {
                userService.delete(userId);
                System.out.println("found and deleted");
                req.setAttribute("successMessage", "User deleted successfully.");
                listUsers(req, resp);

                /*
                 * Not Required to go to success page : Now When the user is deleted, the page
                 * is updated automatically ==> Haroun
                 */
                // req.getRequestDispatcher("/jsp/user/success.jsp").forward(req, resp);

            } else {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while deleting the user: " + e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void viewOrderHistory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        Optional<User> userOpt = userService.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Order> orders = user.getOrders(); // Retrieve the user's orders
            req.setAttribute("orders", orders);
            req.setAttribute("user", user);
            req.getRequestDispatcher("WEB-INF/views/user/orderHistory.jsp").forward(req, resp); // Forward to order
                                                                                                // history page
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

}
