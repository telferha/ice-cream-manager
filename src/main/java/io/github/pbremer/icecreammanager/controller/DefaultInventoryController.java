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
import io.github.pbremer.icecreammanager.service.DefaultInventoryService;

/**
 * @author Patrick Bremer
 */
@RestController("/api/defaults")
public class DefaultInventoryController {

    @Autowired
    public DefaultInventoryService service;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody List<DefaultInventory> getDefaults() {
	return service.findAll();
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public List<DefaultInventory>
            postDefaults(@RequestBody List<DefaultInventory> inv) {

	service.deleteAllInBatch();
	inv.subList(0, 5);
	service.save(inv);

	return inv;

    }
}
