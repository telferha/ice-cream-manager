/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Patrick Bremer
 */
public interface ActivatableService<T extends Serializable, ID extends Serializable>
        extends BaseService<T, ID> {

    public List<T> findWhereIsActiveEquals(boolean isActive);

}
