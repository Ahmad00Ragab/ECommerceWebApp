package gov.iti.jets.services;

import gov.iti.jets.models.Admin;
import gov.iti.jets.repositories.AdminRepository;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import java.util.Optional;


public class AdminService {
    private final AdminRepository adminRepository;

    // Constructor to inject AdminRepository
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 1. Find admin by ID
    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // 2. Find admin by email
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    // 3. Create a new admin
    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // 4. Update admin information
    public void updateAdmin(Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findById((long)admin.getId());
        if (existingAdmin.isPresent()) {
            adminRepository.save(admin);
        } else {
            throw new ObjectNotFoundException("Admin",(long) admin.getId());
        }
    }

    // 5. Delete admin by ID
    public void deleteAdmin(long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            adminRepository.delete(admin.get());
        } else {
            throw new ObjectNotFoundException("Admin", id);
        }
    }
}
