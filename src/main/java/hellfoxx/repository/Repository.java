package hellfoxx.repository;

import java.util.List;

import hellfoxx.entity.Entity;
import hellfoxx.exception.RepositoryException;
import hellfoxx.specification.Specification;

public interface Repository<T extends Entity> {

    boolean add(T entity) throws RepositoryException;

    boolean remove(T entity) throws RepositoryException;

    boolean update(T entity) throws RepositoryException;

    List<T> query(Specification specification) throws RepositoryException;

}
