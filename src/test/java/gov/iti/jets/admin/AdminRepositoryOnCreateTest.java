package gov.iti.jets.admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminRepositoryOnCreateTest {

    @Mock
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a new Admin instance
        admin = new Admin();
        admin.setName("John Doe");
        admin.setEmail("john.doe@gmail.com");
        admin.setPassword("password123");

        // Mock repository save method to return the admin
        when(adminRepository.save(any(Admin.class))).thenAnswer(invocation -> {
            Admin savedAdmin = invocation.getArgument(0);
            savedAdmin.onCreate(); // Manually invoke the onCreate method for the test
            return savedAdmin;
        });
    }

    @AfterEach
    void tearDown() {
        adminRepository = null;
    }

    @Test
    void onCreate_shouldSetDateCreatedAndLastUpdated() {
        // Act: Save the admin, which should trigger onCreate
        Admin savedAdmin = adminRepository.save(admin);

        // Assert: Check if dateCreated and lastUpdated are set
        assertThat(savedAdmin.getDateCreated()).isNotNull();
        assertThat(savedAdmin.getLastUpdated()).isNotNull();

        // The dateCreated and lastUpdated should be roughly the current time
        LocalDateTime now = LocalDateTime.now();
        assertThat(savedAdmin.getDateCreated()).isBeforeOrEqualTo(now);
        assertThat(savedAdmin.getLastUpdated()).isBeforeOrEqualTo(now);

        verify(adminRepository, times(1)).save(admin);
    }
}
