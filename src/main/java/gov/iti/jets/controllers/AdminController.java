package gov.iti.jets.controllers;

import gov.iti.jets.models.Admin;
import gov.iti.jets.services.AdminService;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;


public class AdminController {
    private final AdminService adminService;

    // Constructor to inject the AdminService
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 1. Find admin by ID
    public Admin getAdminById(long id) {
        return adminService.findAdminById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Admin", id));
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
