/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Route;

/**
 * @author Patrick Bremer
 */
@Service
public interface RouteService extends ActivatableService<Route, String> {
    public int countZones(String routeId);

    public void inactivateRoutes(List<Route> routes);
}
