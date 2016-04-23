/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.EndDayInventory;

/**
 * @author Matthew Stockert
 */
@Service
public interface EndDayInventoryService
        extends InstanceService<EndDayInventory, Long> {
}
