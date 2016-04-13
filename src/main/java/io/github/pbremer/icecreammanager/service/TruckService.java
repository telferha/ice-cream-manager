/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Truck;

/**
 * @author Patrick Bremer
 */
@Service
public interface TruckService extends ActivatableService<Truck, String> {

}
