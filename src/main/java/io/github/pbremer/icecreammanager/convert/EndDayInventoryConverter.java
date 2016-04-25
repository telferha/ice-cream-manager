/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.EndDayInventory;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.TruckSalesFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer;
import io.github.pbremer.icecreammanager.service.BeginDayInventoryService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
public class EndDayInventoryConverter
        implements Converter<TruckSalesFlatFileContainer, TruckInstance>,
        InitializingBean {

    @Autowired
    private BeginDayInventoryService inventoryService;

    @Autowired
    private TruckInstanceService truckService;

    private long ms;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public TruckInstance convert(TruckSalesFlatFileContainer source) {
	Date day = new Date(ms);
	TruckInstance truck = truckService.getTruckByDayAndTruckNumber(day,
	        source.getTruckNumber());
	List<BeginDayInventory> beginInventoryList =
	        inventoryService.findByTruckInstance(truck);
	Map<String, Integer> map = createMap(source.getEndOfDayInventory());
	List<EndDayInventory> inventoryList = new ArrayList<EndDayInventory>();
	EndDayInventory endInventory;

	for (BeginDayInventory beginInventory : beginInventoryList) {

	    if (map.containsKey(beginInventory.getIceCreamInstance()
	            .getIceCream().getIceCreamName())) {

		endInventory = new EndDayInventory();
		endInventory.setIceCreamInstance(
		        beginInventory.getIceCreamInstance());
		endInventory
		        .setAmmount(map.get(beginInventory.getIceCreamInstance()
		                .getIceCream().getIceCreamName()));
		endInventory.setPrice(beginInventory.getPrice());
		inventoryList.add(endInventory);
	    }
	}

	truck.setEndDayInventory(inventoryList);

	return truck;
    }

    private Map<String, Integer> createMap(
            List<EndOfDayInventoryFlatFileContainer> endOfDayInventory) {
	Map<String, Integer> map = new TreeMap<String, Integer>();

	for (EndOfDayInventoryFlatFileContainer inventory : endOfDayInventory) {
	    map.put(inventory.getItemNumber(),
	            Integer.valueOf(inventory.getFinalQuantity()));
	}

	return map;
    }

    public void setMs(long ms) {
	this.ms = ms;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	// Assert.notNull(day, "Day must be set");
    }

}
