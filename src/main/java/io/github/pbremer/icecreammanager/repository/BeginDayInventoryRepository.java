/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.TruckInstance;

/**
 * @author Patrick Bremer
 */
@Repository
public interface BeginDayInventoryRepository
        extends DateRangeSearchable<BeginDayInventory, Long> {

    @Query("SELECT i FROM BeginDayInventory i WHERE i.truckInstance = :truckInstanceId")
    public List<BeginDayInventory> getBeginDayInventory(
            @Param("truckInstanceId") long truckInstanceId);

    /**
     * @param truckInstanceId
     * @return
     */
    public List<BeginDayInventory>
            findByTruckInstance(TruckInstance truckInstance);
}
