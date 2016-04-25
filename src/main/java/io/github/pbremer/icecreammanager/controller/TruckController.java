/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.service.BeginDayInventoryService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/truck")
public class TruckController {

    @Autowired
    private TruckInstanceService truckService;

    @Autowired
    private BeginDayInventoryService beginService;

    @RequestMapping(path = "/getTruckIds", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<String> getTruckIds() {
	return truckService.getListOfLatestTrucks();
    }
    
    @Transactional
    @RequestMapping(path = "/getTruckInventory/{truckId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<BeginDayInventory>
            getTruckInventory(@PathVariable("truckId") String truckId) {

	TruckInstance truck = truckService.getLatestTruck(truckId);
	List<BeginDayInventory> inv = beginService.findByTruckInstance(truck);
	for (BeginDayInventory bgi : inv) {
	    Hibernate.initialize(bgi.getIceCreamInstance());
	    Hibernate.initialize(bgi.getIceCreamInstance().getIceCream());
	    Hibernate.initialize(
	            bgi.getIceCreamInstance().getIceCream().getDescription());
	}
	return inv;
    }

}
