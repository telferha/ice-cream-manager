/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import java.util.Date;

import io.github.pbremer.icecreammanager.entity.TruckInstance;

/**
 * @author Patrick Bremer
 */
public interface TruckInstanceService
        extends InstanceService<TruckInstance, Long> {

    public TruckInstance getTruckByDayAndTruckNumber(Date day,
            String truckNumber);
}
