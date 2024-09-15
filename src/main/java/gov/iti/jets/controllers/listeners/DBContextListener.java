/*
package gov.iti.jets.controllers.listeners;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContext;
import org.hibernate.jpa.HibernatePersistenceProvider;


@WebListener
public class DBContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet Context Initialized");

        ServletContext contextObject = servletContextEvent.getServletContext();

        try {
            // Create EntityManagerFactory using the persistence unit name "ecommercePU"
            EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                    new CustomPersistenceUnit(), new CustomPersistenceUnit().getProperties()
            );
            System.out.println("EntityManagerFactory created: " + emf);
            contextObject.setAttribute("emf", emf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet Context Destroyed");
        // Retrieve EntityManagerFactory from ServletContext and close it
        EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent.getServletContext().getAttribute("emf");

        if (emf != null) {
            emf.close(); // Close the factory itself
        }
    }
}
*/
