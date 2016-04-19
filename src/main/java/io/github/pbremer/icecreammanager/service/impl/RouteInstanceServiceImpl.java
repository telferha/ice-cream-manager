/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.RouteInstance;
import io.github.pbremer.icecreammanager.repository.RouteInstanceRepository;
import io.github.pbremer.icecreammanager.service.RouteInstanceService;

/**
 * @author Matthew Stockert
 */
@Service
public class RouteInstanceServiceImpl extends AbstractInstanceService<RouteInstance, Long>
        implements RouteInstanceService {

    private RouteInstanceRepository repository;

    @Autowired
    public RouteInstanceServiceImpl(RouteInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
