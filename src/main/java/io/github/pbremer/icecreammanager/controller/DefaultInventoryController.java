/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.DefaultInventory;
import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.service.DefaultInventoryService;
import io.github.pbremer.icecreammanager.service.IceCreamService;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/defaults")
public class DefaultInventoryController {

    @Autowired
    public DefaultInventoryService defaultService;

    @Autowired
    public IceCreamService iceCreamService;

    @RequestMapping(value = "/view",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody List<DefaultInventory> getDefaults() {
	return defaultService.findAll();
    }

    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody DefaultInventory postDefaults(
            @RequestBody String iceCreamId, @RequestBody int quantity) {

	if (defaultService.findAll().size() <= 5
	        && iceCreamService.existsAndIsActive(iceCreamId)) {
	    IceCream ic = iceCreamService.getOne(iceCreamId);
	    DefaultInventory inv = new DefaultInventory();
	    inv.setActive(true);
	    inv.setAmmount(Math.abs(quantity));
	    inv.setIceCream(ic);
	    defaultService.save(inv);
	    return inv;
	}

	return null;

    }

    @RequestMapping(value = "/remove",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody DefaultInventory removeDefault(@RequestBody long id) {
	return defaultService.delete(id);
    }
}
