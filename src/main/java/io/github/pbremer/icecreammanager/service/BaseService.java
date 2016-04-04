package io.github.pbremer.icecreammanager.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Patrick Bremer
 */
public interface BaseService<T extends Serializable, ID extends Serializable> {

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     * 
     * @param entity
     * @return the saved entity
     */
    public T save(T entity);

    /**
     * Saves all given entities.
     * 
     * @param entities
     * @return the saved entities
     * @throws IllegalArgumentException
     *             in case the given entity is {@literal null}.
     */
    public Iterable<T> save(Iterable<T> entities);

    /**
     * Returns all instances of the type.
     * 
     * @return all entities
     */
    public List<T> findAll();

    /**
     * Returns a {@link Page} of entities meeting the paging restriction
     * provided in the {@code Pageable} object.
     * 
     * @param pageable
     * @return a page of entities
     */
    public Page<T> findAll(Pageable pageable);

    /**
     * Saves all given entities.
     * 
     * @param entities
     * @return the saved entities
     * @throws IllegalArgumentException
     *             in case the given entity is {@literal null}.
     */
    public List<T> batchSave(Iterable<T> entities);

    /**
     * Returns a reference to the entity with the given identifier.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object)
     */
    public T getOne(ID id);
}
