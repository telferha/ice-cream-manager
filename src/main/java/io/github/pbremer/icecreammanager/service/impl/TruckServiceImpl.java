/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Truck;
import io.github.pbremer.icecreammanager.repository.TruckRepository;
import io.github.pbremer.icecreammanager.service.TruckService;

/**
 * @author Patrick Bremer
 */
@Service
public class TruckServiceImpl extends AbstractActivatableService<Truck, String>
        implements TruckService {

    private TruckRepository repository;

    @Autowired
    public TruckServiceImpl(TruckRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
