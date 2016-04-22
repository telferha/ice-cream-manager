/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.TruckInstance;

/**
 * @author Matthew Stockert
 */
@Service
public interface BeginDayInventoryService
        extends InstanceService<BeginDayInventory, Long> {

    public List<BeginDayInventory>
            findByTruckInstance(TruckInstance truckInstance);
}
