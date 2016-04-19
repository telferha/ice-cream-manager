/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.Driver;
import io.github.pbremer.icecreammanager.repository.DriverRepository;
import io.github.pbremer.icecreammanager.service.DriverService;

/**
 * @author Patrick Bremer
 */
public class DriverServiceImpl extends
        AbstractActivatableService<Driver, String> implements DriverService {

    @Autowired
    private DriverRepository repository;

    /**
     * @param repository
     */
    public DriverServiceImpl(DriverRepository repository) {
	super(repository);
	this.repository = repository;
    }

}
