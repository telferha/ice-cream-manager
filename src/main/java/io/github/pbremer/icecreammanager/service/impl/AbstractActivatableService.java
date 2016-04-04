/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.repository.IsActiveSerchable;
import io.github.pbremer.icecreammanager.service.ActivatableService;

/**
 * @author Patrick Bremer
 */
public abstract class AbstractActivatableService<T extends Serializable, ID extends Serializable>
        extends AbstractBaseService<T, ID>
        implements ActivatableService<T, ID> {

    IsActiveSerchable<T, ID> repository;

    /**
     * @param repository
     */
    @SuppressWarnings("unchecked")
    public AbstractActivatableService(IsActiveSerchable<T, ID> repository) {
	super((JpaRepository<T, ID>) repository);
	this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.ActivatableService#
     * findWhereIsActiveEquals(boolean)
     */
    @Override
    public List<T> findWhereIsActiveEquals(boolean isActive) {
	return repository.findByIsActiveEquals(isActive);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
	super.afterPropertiesSet();
	Assert.notNull(repository);
    }

}
