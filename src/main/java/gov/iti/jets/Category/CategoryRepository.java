package gov.iti.jets.Category;

import gov.iti.jets.GenericDao.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CategoryRepository extends GenericDaoImpl {

    public CategoryRepository(Class entityClass, EntityManager entityManager, EntityTransaction transaction) {
        super(entityClass);
    }
}
