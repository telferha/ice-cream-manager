/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Route;
import io.github.pbremer.icecreammanager.repository.RouteRepository;
import io.github.pbremer.icecreammanager.service.RouteService;

/**
 * @author Patrick Bremer
 */
@Service
public class RouteServiceImpl extends AbstractActivatableService<Route, String>
        implements RouteService {

    private RouteRepository repository;

    /**
     * @param repository
     */
    @Autowired
    public RouteServiceImpl(RouteRepository repository) {
	super(repository);
	this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see
     * io.github.pbremer.icecreammanager.service.RouteService#countZones(java.
     * lang.String)
     */
    @Override
    public int countZones(String routeId) {
	return (existsAndIsActive(routeId) ? getOne(routeId).getZones().size()
	        : 0);
    }

    /*
     * (non-Javadoc)
     * @see
     * io.github.pbremer.icecreammanager.service.RouteService#inactivateRoutes(
     * java.util.List)
     */
    @Override
    public void inactivateRoutes(List<Route> routes) {
	List<Route> evictedRoutes = new ArrayList<Route>();
	Route evictedRoute;
	for (Route route : routes) {
	    evictedRoute = getOne(route.getRouteId());
	    evictedRoute.getZones().clear();
	    evictedRoute.setActive(false);
	    repository.save(evictedRoute);
	}

    }

}
