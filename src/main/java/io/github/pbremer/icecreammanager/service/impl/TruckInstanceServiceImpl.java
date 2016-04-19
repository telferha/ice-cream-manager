/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.repository.TruckInstanceRepository;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Matthew Stockert
 */
@Service
public class TruckInstanceServiceImpl extends AbstractInstanceService<TruckInstance, Long>
        implements TruckInstanceService {

    private TruckInstanceRepository repository;

    @Autowired
    public TruckInstanceServiceImpl(TruckInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
