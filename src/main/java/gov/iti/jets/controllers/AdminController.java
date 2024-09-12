package gov.iti.jets.controllers;

import gov.iti.jets.models.Admin;
import gov.iti.jets.services.AdminService;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;




@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object idObject = request.getSession().getAttribute("id");
    
        Long adminId = null;
        if (idObject instanceof Integer) {
            adminId = Long.valueOf((Integer) idObject); // Convert Integer to Long
        } else if (idObject instanceof Long) {
            adminId = (Long) idObject;
        }
    
        if (adminId != null) {
            // Retrieve admin details by adminId
            Optional<Admin> adminOptional = adminService.findAdminById(adminId);
    
            if (adminOptional.isPresent()) {
                Admin admin = adminOptional.get();  // Unwrap the Optional
                request.setAttribute("admin", admin);  // Pass the actual Admin object to the JSP
                request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request, response);
            } else {
                // Handle case where admin is not found
                response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
            }
        } else {
            // Handle case where adminId is not found or invalid
            response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
        }
    }

     // 2. Find admin by email
     public Admin getAdminByEmail(String email) {
        return adminService.findAdminByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Admin", email));
    }

    // 3. Create a new admin
    public void createAdmin(Admin admin) {
        adminService.createAdmin(admin);
    }

    // 4. Update admin information
    public void updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
    }

    // 5. Delete admin by ID
    public void deleteAdmin(int id) {
        adminService.deleteAdmin(id);
    }

}


