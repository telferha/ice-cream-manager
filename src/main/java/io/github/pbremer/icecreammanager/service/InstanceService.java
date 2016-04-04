/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Patrick Bremer
 */
public interface InstanceService<T extends Serializable, ID extends Serializable>
        extends BaseService<T, ID> {

    public List<T> findByDayBetween(Date begin, Date end);
}
