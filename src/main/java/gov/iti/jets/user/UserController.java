package gov.iti.jets.user;

import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import gov.iti.jets.system.exceptions.ValidationException;
import gov.iti.jets.user.converter.UserDtoToUserConverter;
import gov.iti.jets.user.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


            switch (action) {
                case "view":
                    viewUser(req, resp);
                    break;
                case "list":
                    listUsers(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }

    }

    private void viewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        Optional<User> userOpt = userService.findById(userId);

        if (userOpt.isPresent()) {
            req.setAttribute("user", userOpt.get().getUsername());
            req.getRequestDispatcher("/jsp/user/view.jsp").forward(req, resp);
        } else {
            throw new ObjectNotFoundException("User", userId);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            switch (action) {
                case "create":
                    createUser(req, resp);
                    break;
                case "update":
                    updateUser(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
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

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Create a User object with provided input
        User user = new User(username, email, password, LocalDate.now(), LocalDate.now());

        // Perform validation
        List<String> validationErrors = userService.createUserValidation(user);

        if (!validationErrors.isEmpty()) {
            // If validation fails, set errors in request and forward to JSP
            req.setAttribute("errors", validationErrors);
            req.getRequestDispatcher("/jsp/user/create.jsp").forward(req, resp);
            return;
        }

        // Check if the username already exists
        if (userService.findUserByUsername(username).isPresent()) {
            req.setAttribute("error", "Username already exists.");
            req.getRequestDispatcher("/jsp/user/create.jsp").forward(req, resp);
            return;
        }

        // Save user to the database after validation
        userService.save(user);
        req.setAttribute("successMessage", "User created successfully.");
        req.getRequestDispatcher("/jsp/user/success.jsp").forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getParameter("userId"));

        // Create the UserDto from the request parameters
        UserDto userDto = new UserDto(
                req.getParameter("username"),
                req.getParameter("email"),
                req.getParameter("password"), // Ensure you handle passwords securely
                req.getParameter("phone"),
                req.getParameter("city"),
                req.getParameter("country"),
                req.getParameter("street")
        );

        // Convert the UserDto to a User object
        User user = UserDtoToUserConverter.convert(userDto, userId);

        try {
            // Call the service to update the user
            userService.update(userId, user);

            // Success, forward to success page
            req.setAttribute("successMessage", "User updated successfully.");
            req.getRequestDispatcher("/jsp/user/success.jsp").forward(req, resp);

        } catch (ValidationException e) {
            // Validation failed, send validation errors to the JSP
            req.setAttribute("errors", e.getValidationErrors());
            req.getRequestDispatcher("/jsp/user/update.jsp").forward(req, resp);
        } catch (ObjectNotFoundException e) {
            // User not found
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        if (userService.existsById(userId)) {
            userService.delete(userId);
            req.setAttribute("successMessage", "User deleted successfully.");
            req.getRequestDispatcher("/jsp/user/success.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "User not found.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
