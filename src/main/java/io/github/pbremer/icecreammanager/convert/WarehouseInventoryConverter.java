/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadInventoryFlatFileContainer;
import io.github.pbremer.icecreammanager.utils.NumberHelper;

/**
 * @author Patrick Bremer
 */
public class WarehouseInventoryConverter implements
        Converter<LoadInventoryFlatFileContainer, WarehouseInventory> {

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public WarehouseInventory convert(LoadInventoryFlatFileContainer source) {
	WarehouseInventory inventory = new WarehouseInventory();
	inventory.setQuantity(Long.valueOf(source.getWareHouseQuantity()));
	IceCream iceCream = new IceCream();
	iceCream.setIceCreamName(source.getItemNumber());
	if (StringUtils.trimToEmpty(source.getDescription()).isEmpty()) {
	    iceCream.setDescription(source.getDescription());
	}
	inventory.setSalesPrice(
	        NumberHelper.convertPenniesStringToDecimal(source.getPrice()));
	inventory.setIceCream(iceCream);
	return inventory;
    }

}
