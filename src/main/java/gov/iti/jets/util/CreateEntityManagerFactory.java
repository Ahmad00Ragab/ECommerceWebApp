package gov.iti.jets.util;

import org.hibernate.jpa.HibernatePersistenceProvider;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



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

