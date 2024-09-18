package gov.iti.jets.system.persistence;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class CreateEntityManagerFactory {

    private static volatile EntityManagerFactory entityManagerFactory;

    private CreateEntityManagerFactory() {}

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

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            close();
            System.out.println("EntityManagerFactory closed.");
        }));
    }
}
