/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadInventoryFlatFileContainer;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;

/**
 * @author Patrick Bremer
 */
public class LoadInventoryValidator implements Validator {

    @Autowired
    private WarehouseInventoryService service;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new LoadInventoryFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	LoadInventoryFlatFileContainer arg =
	        (LoadInventoryFlatFileContainer) target;

	List<WarehouseInventory> warehouseinventory = service.findAll();

	if (!(Integer.valueOf(arg.getPrice()) > 0)) {
	    errors.reject("loadinventory.price.notset",
	            "You must specify price");
	    return;
	}
	boolean found = false;
	for (WarehouseInventory inv : warehouseinventory) {
	    found = inv.getIceCream().getIceCreamName()
	            .equalsIgnoreCase(arg.getItemNumber());
	}
	if (!found && StringUtils.trimToEmpty(arg.getDescription()).isEmpty()) {
	    errors.reject("loadinventory.description.notset",
	            "You must specify description for new inventory");
	}

    }

}
