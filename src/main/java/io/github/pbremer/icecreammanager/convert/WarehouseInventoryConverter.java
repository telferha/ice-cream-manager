/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.entity.WarehouseInventory;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadInventoryFlatFileContainer;
import io.github.pbremer.icecreammanager.repository.WarehouseRepository;
import io.github.pbremer.icecreammanager.utils.NumberHelper;

/**
 * @author Patrick Bremer
 */
public class WarehouseInventoryConverter implements
        Converter<LoadInventoryFlatFileContainer, WarehouseInventory> {

    @Autowired
    private WarehouseRepository warehouse;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public WarehouseInventory convert(LoadInventoryFlatFileContainer source) {
	WarehouseInventory inventory = new WarehouseInventory();
	inventory.setActive(true);
	inventory.setQuantity(Long.valueOf(source.getWareHouseQuantity()));
	IceCream iceCream = new IceCream();
	iceCream.setActive(true);
	iceCream.setIceCreamName(source.getItemNumber());
	if (StringUtils.trimToEmpty(source.getDescription()).isEmpty()) {
	    iceCream.setDescription(source.getDescription());
	}
	inventory.setSalesPrice(
	        NumberHelper.convertPenniesStringToDecimal(source.getPrice()));
	inventory.setIceCream(iceCream);
	return inventory;
    }

    @BeforeWrite
    public void beforeWrite(List<? extends WarehouseInventory> items) {
	warehouse.deleteAll();
    }

}
