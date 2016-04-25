/**
 * 
 */
package io.github.pbremer.icecreammanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;

/**
 * @author Patrick Bremer
 */
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseInventoryController {

    @Autowired
    private WarehouseInventoryService warehouse;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WarehouseInventory> getWarehouse() {
	return warehouse.findAll();
    }

}
