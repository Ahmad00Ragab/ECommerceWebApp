package gov.iti.jets.commonServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage;

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    errorMessage = "Resource not found.";
                    break;
                case 500:
                    errorMessage = "Internal server error.";
                    break;
                default:
                    errorMessage = "An unexpected error occurred.";
            }
        } else if (throwable != null) {
            errorMessage = throwable.getMessage();
        } else {
            errorMessage = "An unknown error occurred.";
        }

        // Log the error details
        if (throwable != null) {
            log("Exception: " + throwable.getMessage(), throwable);
        }

        // Set error message and forward to error JSP
        req.setAttribute("errorMessage", errorMessage);
        req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
    }
}
