package gov.iti.jets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import gov.iti.jets.system.exceptions.ObjectNotFoundException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
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
        } catch (ObjectNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private void viewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        User user = userService.findById(userId);

        if (userService.existsById(userId)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/jsp/user/view.jsp").forward(req, resp);
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
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (ObjectNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Form validation logic here (e.g., check empty fields)
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/WEB-INF/jsp/user/create.jsp").forward(req, resp);
            return;
        }

        userService.save(new User(username, email, password, LocalDate.now(), LocalDate.now()));
        resp.sendRedirect("/user?action=list");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String newUsername = req.getParameter("username");
        User user = userService.findById(userId);

        user.setUsername(newUsername);
        String newphone = req.getParameter("phone");
        String newcity = req.getParameter("city");
        String newcountry = req.getParameter("country");
        String newstreet = req.getParameter("street");

        userService.update(userId, user);
        resp.sendRedirect("/user?action=view&userId=" + userId);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        userService.delete(userId);
        resp.sendRedirect("/user?action=list");
    }
}