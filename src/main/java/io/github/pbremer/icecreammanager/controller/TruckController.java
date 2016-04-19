/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.entity.IceCreamInstance;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/truck")
public class TruckController {

    @RequestMapping(path = "/getTruckIds", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<String> getTruckIds() {
	List<String> truckIds = new ArrayList<String>();
	truckIds.add("0001");
	truckIds.add("0002");
	truckIds.add("0040");
	return truckIds;
    }

    @ApiOperation(value = "getTruckInventory", nickname = "getTruckInventory")
    @RequestMapping(path = "/getTruckInventory/{truckId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "truckId", value = "The truck's ID",
                    required = true, dataType = "string", paramType = "path") })
    public @ResponseBody List<BeginDayInventory>
            getTruckInventory(@PathVariable("truckId") String truckId) {
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
