/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.DriverInstance;
import io.github.pbremer.icecreammanager.repository.DriverInstanceRepository;
import io.github.pbremer.icecreammanager.service.DriverInstanceService;

/**
 * @author Matthew Stockert
 */
@Service
public class DriverInstanceServiceImpl extends AbstractInstanceService<DriverInstance, Long>
        implements DriverInstanceService {

    private DriverInstanceRepository repository;

    @Autowired
    public DriverInstanceServiceImpl(DriverInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
