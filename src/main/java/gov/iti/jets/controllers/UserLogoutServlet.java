package gov.iti.jets.controllers;



import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/logout")
public class UserLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         // Invalidate the current session, if it exists
         HttpSession session = request.getSession(false); 
         if (session != null) {
             session.invalidate(); // Ends the session
         }

//        // delete cookies
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
//        }

        // Redirect to the login page or home page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}

