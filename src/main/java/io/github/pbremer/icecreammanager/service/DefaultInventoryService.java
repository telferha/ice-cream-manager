/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.DefaultInventory;

/**
 * @author Matthew Stockert
 */
@Service
public interface DefaultInventoryService extends ActivatableService<DefaultInventory, String> {

}
