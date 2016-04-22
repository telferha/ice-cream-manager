/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;

/**
 * @author Patrick Bremer
 */
public interface WarehouseRepository
        extends IsActiveSerchable<WarehouseInventory, Long> {

    @Query("SELECT w.salesPrice FROM WarehouseInventory w WHERE w.iceCream = :inventoryId "
            + "AND w.active = true")
    public BigDecimal getCurrentPrice(@Param("inventoryId") long inventoryId);
}
