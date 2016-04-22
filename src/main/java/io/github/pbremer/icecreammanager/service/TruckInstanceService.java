/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.util.Date;

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
}
