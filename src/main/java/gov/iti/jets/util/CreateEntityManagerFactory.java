package gov.iti.jets.util;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class CreateEntityManagerFactory {

    // Volatile keyword ensures the visibility of changes across threads
    private static volatile EntityManagerFactory entityManagerFactory;

    private CreateEntityManagerFactory() {
        // Private constructor to prevent instantiation
    }

    // Thread-safe singleton creation
    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            synchronized (CreateEntityManagerFactory.class) {
                if (entityManagerFactory == null) {
                    try {
                        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
                        entityManagerFactory = provider.createContainerEntityManagerFactory(
                                new CustomPersistenceUnit(),
                                new CustomPersistenceUnit().getProperties()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to create EntityManagerFactory", e);
                    }
                }
            }
        }
        return entityManagerFactory;
    }

    // Method to close the factory
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    // Add this shutdown hook to ensure the factory is closed when the app shuts down
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            close();
            System.out.println("EntityManagerFactory closed.");
        }));
    }
}
