package twolak.springframework.twspringpetclinic.services;

import java.util.Set;

/**
 * @author twolak
 *
 */
public interface CrudService<T,ID> {
    T findById(ID id);
    T save(T object);
    Set<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
}
