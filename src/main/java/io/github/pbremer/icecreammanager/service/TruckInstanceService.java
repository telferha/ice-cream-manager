/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.TruckInstance;

/**
 * @author Matthew Stockert
 */
@Service
public interface TruckInstanceService
        extends InstanceService<TruckInstance, Long> {

    public TruckInstance getTruckByDayAndTruckNumber(Date day,
            String truckNumber);

    /**
     * @param day
     * @param routeNumber
     * @return
     */
    public TruckInstance getTruckByDayAndRouteNumber(Date day,
            String routeNumber);

    public TruckInstance getLatestTruck(String truckNumber);

    public List<String> getListOfLatestTrucks();
}
