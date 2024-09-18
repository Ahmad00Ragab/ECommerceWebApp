package gov.iti.jets.system.persistence;

import jakarta.persistence.EntityManager;

/**
 * Utility class to manage EntityManager instances using ThreadLocal.
 */
public class EntityManagerUtil {
    // ThreadLocal to hold EntityManager instances per thread
    private static final ThreadLocal<EntityManager> MANAGERS = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private EntityManagerUtil() {}

    /**
     * Sets the EntityManager for the current thread.
     *
     * @param em EntityManager to set
     */
    public static void setEntityManager(EntityManager em) {
        MANAGERS.set(em);
    }

    /**
     * Retrieves the EntityManager for the current thread.
     *
     * @return EntityManager associated with the current thread
     */
    public static EntityManager getEntityManager() {
        return MANAGERS.get();
    }

    /**
     * Removes the EntityManager from the current thread.
     */
    public static void removeEntityManager() {
        MANAGERS.remove();
    }
}
