/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.repository.WarehouseRepository;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;

/**
 * @author Patrick Bremer
 */
@Service
public class WharehouseInventoryServiceImpl
        extends AbstractActivatableService<WarehouseInventory, Long>
        implements WarehouseInventoryService {

    private WarehouseRepository repository;

    @Autowired
    public WharehouseInventoryServiceImpl(WarehouseRepository repository) {
	super(repository);
	this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.WarehouseInventoryService#
     * getPrice(long)
     */
    @Override
    public BigDecimal getPrice(long iceCreamId) {
	return repository.getCurrentPrice(iceCreamId);
    }

}
