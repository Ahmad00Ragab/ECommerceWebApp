package gov.iti.jets.util;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;


public class CreateEntityManagerFactory{

    private static EntityManagerFactory entityManagerFactory;


    private CreateEntityManagerFactory() {
    }


    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            synchronized (CreateEntityManagerFactory.class) {
                if (entityManagerFactory == null) {
                    HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
                    entityManagerFactory = provider.createContainerEntityManagerFactory(new CustomPersistenceUnit(), new CustomPersistenceUnit().getProperties());
                }
            }
        }
        return entityManagerFactory;
    }


    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    
}

