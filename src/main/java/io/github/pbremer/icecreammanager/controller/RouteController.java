/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.entity.IceCreamInstance;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/route")
public class RouteController {

    @RequestMapping(path = "/getRouteIds", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<String> getRouteIds() {
	List<String> routeIds = new ArrayList<String>();
	routeIds.add("0001R");
	routeIds.add("0002R");
	routeIds.add("0040R");
	return routeIds;
    }

    //@CrossOrigin
    @RequestMapping(path = "/getRouteInventory/{routeId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<BeginDayInventory>
            getRouteInventory(@PathVariable("routeId") String routeId) {
	List<BeginDayInventory> inventory = new ArrayList<BeginDayInventory>();
	BeginDayInventory inv = new BeginDayInventory();
	inv.setAmmount(8);
	IceCreamInstance iceCreamInstance = new IceCreamInstance();
	iceCreamInstance.setIceCreamInstanceId(8643L);
	iceCreamInstance.setSupplierPrice(new BigDecimal("2.99"));
	IceCream iceCream = new IceCream();
	iceCream.setDescription("Description");
	iceCream.setIceCreamName("Fudgesicle");
	iceCreamInstance.setIceCream(iceCream);
	inv.setIceCreamInstance(iceCreamInstance);
	inventory.add(inv);
	return inventory;
    }
    
}