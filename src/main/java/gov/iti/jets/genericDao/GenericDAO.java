package gov.iti.jets.genericDao;

import java.util.Set;

public interface GenericDAO<T> {
    T save(T entity);
  
    Set<T> findAll();

    T findById(java.lang.Integer id);

    boolean delete(Integer id);

    boolean delete(T entity);

    T update (T entity);
}