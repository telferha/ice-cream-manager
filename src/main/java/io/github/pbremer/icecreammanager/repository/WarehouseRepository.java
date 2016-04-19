/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;

/**
 * @author Patrick Bremer
 */
public interface WarehouseRepository
        extends IsActiveSerchable<WarehouseInventory, Long> {

}
