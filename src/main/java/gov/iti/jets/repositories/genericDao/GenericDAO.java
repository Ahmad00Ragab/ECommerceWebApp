package gov.iti.jets.repositories.genericDao;

import java.util.Optional;
import java.util.Set;


public interface GenericDAO<T> {
    T save(T entity);
  
    Set<T> findAll();

    Optional<T> findById(Long id);

    boolean delete(Long id);

    boolean delete(T entity);

    T update (T entity);

    boolean existsById(Long id);
}