/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.EndDayInventory;

/**
 * @author Patrick Bremer
 */
@Repository
public interface EndDayInventoryRepository
        extends DateRangeSearchable<EndDayInventory, Long> {

}
