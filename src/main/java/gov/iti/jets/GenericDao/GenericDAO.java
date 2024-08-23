package gov.iti.jets.GenericDao;

import java.util.Set;

public interface GenericDAO<T> {
    T save(T entity);
  
    Set<T> findAll();

    T findById(java.lang.Integer id);

    void delete(Integer id);
}