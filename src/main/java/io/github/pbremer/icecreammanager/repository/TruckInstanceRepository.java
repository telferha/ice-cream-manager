package io.github.pbremer.icecreammanager.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.TruckInstance;

/**
 * Interface to persist {@link TruckInstance} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface TruckInstanceRepository
        extends DateRangeSearchable<TruckInstance, Long> {

    @Query("SELECT ti FROM TruckInstance ti JOIN ti.truck t WHERE ti.day = :day and t.truckNumber = :truckNumber")
    public TruckInstance findByDayAndTruckNumber(@Param("day") Date day,
            @Param("truckNumber") String truckNumber);

    @Query("SELECT t FROM TruckInstance t WHERE t.day = :day and t.routeInstance = :routeNumber")
    public TruckInstance findByDayAndRouteNumber(@Param("day") Date day,
            @Param("routeNumber") String routeNumber);
}
