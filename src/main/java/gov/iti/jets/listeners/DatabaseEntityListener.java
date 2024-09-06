package gov.iti.jets.listeners;

import gov.iti.jets.system.persistence.CustomPersistenceUnit;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.jpa.HibernatePersistenceProvider;

@WebListener
public class DatabaseEntityListener implements ServletContextListener {

    private static EntityManagerFactory emf;
    private final CustomPersistenceUnit cpu;
    public DatabaseEntityListener() {
        cpu= new CustomPersistenceUnit();
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(cpu, cpu.getProperties());
        sce.getServletContext().setAttribute("emf", emf);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       if(emf != null) {
           emf.close();
       }

    }


    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }



}
