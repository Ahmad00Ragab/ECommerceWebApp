package gov.iti.jets.util;

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
                    entityManagerFactory = Persistence.createEntityManagerFactory("ecommercePU");
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

