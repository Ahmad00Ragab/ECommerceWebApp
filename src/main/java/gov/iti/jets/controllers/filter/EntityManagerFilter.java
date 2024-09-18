package gov.iti.jets.controllers.filter;

import gov.iti.jets.system.persistence.CreateEntityManagerFactory;
import gov.iti.jets.system.persistence.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.*;
import java.io.IOException;

public class EntityManagerFilter implements Filter {

    private static final EntityManagerFactory entityManagerFactory = CreateEntityManagerFactory.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        EntityManager em = null;
        try {
            // Create a new EntityManager and set it in ThreadLocal
            em = entityManagerFactory.createEntityManager();
            System.out.println("Entity Manager created");
            EntityManagerUtil.setEntityManager(em);

            // Begin transaction if needed
            em.getTransaction().begin();

            // Proceed with the next filter or servlet
            chain.doFilter(request, response);

            // Commit transaction if still active
            if (em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("EntityManagerFilter encountered an error", e);
        } finally {
            // Clean up the EntityManager
            EntityManagerUtil.removeEntityManager();
            if (em != null && em.isOpen()) {
                System.out.println("EntityManager closed");
                em.close();
            }
        }
    }

    @Override
    public void destroy() {
        CreateEntityManagerFactory.close();
    }
}
