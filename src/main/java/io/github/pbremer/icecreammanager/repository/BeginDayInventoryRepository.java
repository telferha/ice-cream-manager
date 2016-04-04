/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;

/**
 * @author Patrick Bremer
 */
@Repository
public interface BeginDayInventoryRepository
        extends DateRangeSearchable<BeginDayInventory, Long> {

}
