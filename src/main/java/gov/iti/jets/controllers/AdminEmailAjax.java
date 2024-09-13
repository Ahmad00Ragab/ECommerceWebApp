package gov.iti.jets.controllers;




/* ==================================================================================== */
/*                            packages Imports                                          */
/* ==================================================================================== */
import java.io.IOException;
import java.util.Optional;

import gov.iti.jets.models.Admin;
import gov.iti.jets.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.*;









/* ==================================================================================== */
/*                            AdminEmailAjax Servlet                                    */
/* ==================================================================================== */
@WebServlet("/AdminEmailAjax")
public class AdminEmailAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;


        private AdminService adminService = new AdminService();


    /* ============ Override the doPost method to handle incoming request that sent by post  ============ */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        /* Get the username and password that the user entered them, from the incoming request */
        String email = request.getParameter("email");
        
    
        /* Create a userDao to access the Database and check for credentiality */
        Admin admin    = adminService.checkEmail(email);


        if (admin != null) {
     
            response.setContentType("text/html");
           
            response.getWriter().println("valid Email");            

        } else {
            response.setContentType("text/html");
            response.getWriter().println("invlid Email");
        }
    }
}
