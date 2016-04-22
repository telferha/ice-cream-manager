/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Route;

/**
 * @author Patrick Bremer
 */
@Service
public interface RouteService extends ActivatableService<Route, String> {

}
