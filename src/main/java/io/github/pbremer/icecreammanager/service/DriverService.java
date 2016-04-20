/**
 * 
 */
package io.github.pbremer.icecreammanager.service;


import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Driver;

/**
 * @author Matthew Stockert
 */
@Service
public interface DriverService extends ActivatableService<Driver, String> {

}
