/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.City;

/**
 * @author Patrick Bremer
 */
@Service
public interface CityService extends ActivatableService<City, String> {

}
