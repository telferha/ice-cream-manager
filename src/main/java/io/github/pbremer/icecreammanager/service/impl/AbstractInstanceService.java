/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.repository.DateRangeSearchable;
import io.github.pbremer.icecreammanager.service.InstanceService;

/**
 * @author Patrick Bremer
 */
public abstract class AbstractInstanceService<T extends Serializable, ID extends Serializable>
        extends AbstractBaseService<T, ID> implements InstanceService<T, ID> {

    private DateRangeSearchable<T, ID> repository;

    /**
     * @param repository
     */
    @SuppressWarnings("unchecked")
    public AbstractInstanceService(DateRangeSearchable<T, ID> repository) {
	super((JpaRepository<T, ID>) repository);
	this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.InstanceService#
     * findByDayBetween(java.util.Date, java.util.Date)
     */
    @Override
    public List<T> findByDayBetween(Date begin, Date end) {
	return repository.findByDayBetween(begin, end);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
	super.afterPropertiesSet();
	Assert.notNull(repository);
    }

}
