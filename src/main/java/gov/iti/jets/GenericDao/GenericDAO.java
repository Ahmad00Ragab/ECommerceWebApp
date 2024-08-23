package gov.iti.jets.GenericDao;

import java.util.Set;

public interface GenericDAO<T> {
    T save(T entity);
  
    Set<T> findAll();

    default T findById(T id) {
        return null;
    }

    default void delete(T id) {
    }

    T findById(java.lang.Integer id);

    void delete(Integer id);
}