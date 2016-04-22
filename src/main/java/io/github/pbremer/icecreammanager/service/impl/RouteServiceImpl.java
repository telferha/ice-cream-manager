/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

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

}
