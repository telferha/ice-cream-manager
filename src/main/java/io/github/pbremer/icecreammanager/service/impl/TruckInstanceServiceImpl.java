/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.repository.TruckInstanceRepository;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
public class TruckInstanceServiceImpl
        extends AbstractInstanceService<TruckInstance, Long>
        implements TruckInstanceService {

    @Autowired
    private TruckInstanceRepository repository;

    /**
     * @param repository
     */
    public TruckInstanceServiceImpl(TruckInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }

    public TruckInstance getTruckByDayAndTruckNumber(Date day,
            String truckNumber) {
	return repository.findByDayAndTruckNumber(day, truckNumber);
    }

}
