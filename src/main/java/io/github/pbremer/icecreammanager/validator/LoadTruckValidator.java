/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer;
import io.github.pbremer.icecreammanager.service.IceCreamService;
import io.github.pbremer.icecreammanager.service.TruckService;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;

/**
 * @author Patrick Bremer
 */
public class LoadTruckValidator implements Validator {

    @Autowired
    private WarehouseInventoryService warehouseService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private IceCreamService iceCreamService;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new LoadTruckFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	LoadTruckFlatFileContainer arg = (LoadTruckFlatFileContainer) target;
	List<WarehouseInventory> whi = warehouseService.findAll();
	if (!truckService.existsAndIsActive(arg.getTruckNumber())) {
	    errors.reject("loadtruck.truck.dne", "Cannot load truck");
	}
	for (WarehouseInventory inv : whi) {
	    for (ItemAdjustmentFlatFileContainer item : arg
	            .getItemAdjustments()) {
		if (inv.getIceCream().getIceCreamName()
		        .equalsIgnoreCase(item.getItemNumber())) {
		    if (inv.getQuantity() - Integer
		            .valueOf(item.getAdjustmentQuantity()) < 0) {
			errors.reject("loadtruck.quantity.belowzero",
			        "Quantity cannot be below 0");
			return;
		    }
		}
		if (!iceCreamService.existsAndIsActive(item.getItemNumber())) {
		    errors.reject("loadtruck.icecream.dne",
		            "Ice cream cannot be loaded");
		}
	    }
	}
    }

}
