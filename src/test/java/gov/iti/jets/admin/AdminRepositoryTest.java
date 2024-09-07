package gov.iti.jets.admin;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import java.util.*;



class AdminRepositoryTest {

    @Mock
    private AdminRepository adminRepository;

    private Admin admin1;
    private Admin admin2;

    // @BeforeEach
    // void setUp() {
    //     MockitoAnnotations.openMocks(this);
    
    //     // Create Admin instances
    //     admin1 = new Admin("John Doe", "john.doe@gmail.com", "password123", LocalDateTime.now(), LocalDateTime.now(), "System");
    //     admin2 = new Admin("Jane Doe", "jane.doe@gmail.com", "password456", LocalDateTime.now(), LocalDateTime.now(), "Admin");
    
    //     Set<Admin> adminSet = new HashSet<>();
    //     adminSet.add(admin1);
    //     adminSet.add(admin2);
    
    //     // Convert Set<Admin> to List<Admin>
    //     List<Admin> adminList = new ArrayList<>(adminSet);
    
    //     // Mock repository methods
    //     when(adminRepository.findAll()).thenReturn(adminList);
    //     when(adminRepository.findById(1L)).thenReturn(Optional.of(admin1)); // wrap in Optional
    //     when(adminRepository.save(any(Admin.class))).thenReturn(admin1);
    //     when(adminRepository.findByEmail("john.doe@gmail.com")).thenReturn(Optional.of(admin1)); // wrap in Optional
    // }
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create Admin instances
        admin1 = new Admin("John Doe", "john.doe@gmail.com", "password123", LocalDateTime.now(), LocalDateTime.now(), "System");
        admin2 = new Admin("Jane Doe", "jane.doe@gmail.com", "password456", LocalDateTime.now(), LocalDateTime.now(), "Admin");

        Set<Admin> adminSet = new HashSet<>();
        adminSet.add(admin1);
        adminSet.add(admin2);

        // Mock repository methods
        when(adminRepository.findAll()).thenReturn(adminSet); // Correct the return type to Set<Admin>
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin1)); 
        when(adminRepository.save(any(Admin.class))).thenReturn(admin1);
        when(adminRepository.findByEmail("john.doe@gmail.com")).thenReturn(Optional.of(admin1));
    }

    @AfterEach
    void tearDown() {
        adminRepository = null;
    }

    // @Test
    // void findAll() {
    //     List<Admin> admins = adminRepository.findAll();
    //     assertEquals(2, admins.size());
    //     assertTrue(admins.contains(admin1));

    //     verify(adminRepository, times(1)).findAll();
    // }

    @Test
    void findAll() {
        Set<Admin> admins = adminRepository.findAll();  // Change to Set<Admin>
        assertEquals(2, admins.size());
        assertTrue(admins.contains(admin1));

        verify(adminRepository, times(1)).findAll();
    }

    
    @Test
    void findById() {

        Optional<Admin> foundAdmin = adminRepository.findById(1L);
        // Ensure the Optional is not empty
        assertTrue(foundAdmin.isPresent(), "Admin should be found");

        // Unwrap the Optional and get the Admin
        assertEquals("John Doe", foundAdmin.get().getName());
    }

    @Test
    void findByIdNotFound() {
        // Given
        given(adminRepository.findById(any(Long.class))).willThrow(EntityNotFoundException.class);

        // When
        Throwable thrown = Assertions.catchThrowable(() -> {
            Optional<Admin> foundAdmin = adminRepository.findById(1L);
        });

        // Then
        Assertions.assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        verify(adminRepository, times(1)).findById(1L);
    }


    @Test
    void save() {
        Admin newAdmin = new Admin("New Admin", "new.admin@gmail.com", "password789", LocalDateTime.now(),
                LocalDateTime.now(), "System");

        // Update the mock to return Optional.of(newAdmin) instead of newAdmin directly
        given(this.adminRepository.save(newAdmin)).willReturn(newAdmin);
        given(this.adminRepository.findByEmail("new.admin@gmail.com")).willReturn(Optional.of(newAdmin));

        // Call the save method
        this.adminRepository.save(newAdmin);

        // Retrieve the saved admin, expecting an Optional<Admin>
        Optional<Admin> savedAdminOptional = adminRepository.findByEmail("new.admin@gmail.com");

        // Unwrap the Optional to get the Admin instance
        assertTrue(savedAdminOptional.isPresent(), "Admin should be found");
        Admin savedAdmin = savedAdminOptional.get();
        assertEquals("New Admin", savedAdmin.getName());

        // Verify that save was called once
        verify(adminRepository, times(1)).save(newAdmin);
    }


    @Test
    void update() {
        admin1.setEmail("updated_email@gmail.com");
        adminRepository.update(admin1);
        assertEquals("updated_email@gmail.com", admin1.getEmail());

        verify(adminRepository, times(1)).update(admin1);
    }

    @Test
    void delete() {
        adminRepository.delete(admin1);
        verify(adminRepository, times(1)).delete(admin1);
    }

    @Test
    void findByEmail() {
        Optional<Admin> foundAdmin = adminRepository.findByEmail("john.doe@gmail.com");
        
        // Ensure the Optional is not empty
        assertTrue(foundAdmin.isPresent(), "Admin should be found");

        // Unwrap the Optional and get the Admin
        assertEquals("john.doe@gmail.com", foundAdmin.get().getEmail());
    }

}
