/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.repository.TruckInstanceRepository;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Matthew Stockert
 */
@Service
public class TruckInstanceServiceImpl
        extends AbstractInstanceService<TruckInstance, Long>
        implements TruckInstanceService {

    private TruckInstanceRepository repository;

    @Autowired
    public TruckInstanceServiceImpl(TruckInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }

    @Override
    public TruckInstance getTruckByDayAndTruckNumber(Date day,
            String truckNumber) {
	return repository.findByDayAndTruckNumber(day, truckNumber);
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.TruckInstanceService#
     * getTruckByDayAndRouteNumber(java.util.Date, java.lang.String)
     */
    @Override
    public TruckInstance getTruckByDayAndRouteNumber(Date day,
            String routeNumber) {
	return repository.findByDayAndRouteNumber(day, routeNumber);
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.TruckInstanceService#
     * getLatestTruck(java.lang.String)
     */
    @Override
    public TruckInstance getLatestTruck(String truckNumber) {
	Date day = repository.getCurrentDate();
	return getTruckByDayAndTruckNumber(day, truckNumber);
    }

    @Override
    public List<String> getListOfLatestTrucks() {
	return repository.getTrucksOn(repository.getCurrentDate());
    }

}
