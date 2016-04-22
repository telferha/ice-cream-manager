/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;

/**
 * @author Patrick Bremer
 */
@Service
public interface WarehouseInventoryService
        extends ActivatableService<WarehouseInventory, Long> {

    public BigDecimal getPrice(long iceCreamId);
}
