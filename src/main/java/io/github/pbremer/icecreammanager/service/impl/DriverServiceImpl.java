/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Driver;
import io.github.pbremer.icecreammanager.repository.DriverRepository;
import io.github.pbremer.icecreammanager.service.DriverService;

/**
 * @author Matthew Stockert
 */
@Service
public class DriverServiceImpl extends AbstractActivatableService<Driver, String>
        implements DriverService {

    private DriverRepository repository;

    @Autowired
    public DriverServiceImpl(DriverRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
