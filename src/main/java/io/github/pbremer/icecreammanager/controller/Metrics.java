/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/metrics")
public class Metrics {

    @Autowired
    private TruckInstanceService tiService;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TruckInstance> getMetrics(
            @RequestParam(value = "from", required = true) Long from,
            @RequestParam(value = "to", required = false) Long to) {
	if (to == null) {
	    to = from;
	}
	List<TruckInstance> truckInstnaceList =
	        tiService.findByDayBetween(new Date(from), new Date(to));

	for (TruckInstance t : truckInstnaceList) {
	    Hibernate.initialize(t.getDriverInstance());
	    Hibernate.initialize(t.getRouteInstance());
	    Hibernate.initialize(t.getDriverInstance());
	    Hibernate.initialize(t.getBeginDayInventory());
	    Hibernate.initialize(t.getEndDayInventory());
	    Hibernate.initialize(t.getInventoryLoss());
	}

	return truckInstnaceList;

    }

}
