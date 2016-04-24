package io.github.pbremer.icecreammanager.repository;

import java.util.Date;
import java.util.List;

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

    @Query("SELECT t FROM TruckInstance t JOIN t.routeInstance ri JOIN ri.route r "
            + "WHERE t.day = :day AND r.routeId = :routeNumber")
    public TruckInstance findByDayAndRouteNumber(@Param("day") Date day,
            @Param("routeNumber") String routeNumber);

    @Query("SELECT MAX(t.day) FROM TruckInstance t")
    public Date getCurrentDate();

    @Query("SELECT t.truckNumber FROM TruckInstance ti JOIN ti.truck t WHERE ti.day = :day")
    public List<String> getTrucksOn(@Param("day") Date day);
}
