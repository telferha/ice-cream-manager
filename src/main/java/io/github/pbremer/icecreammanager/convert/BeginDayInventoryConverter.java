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
import io.github.pbremer.icecreammanager.entity.DefaultInventory;
import io.github.pbremer.icecreammanager.entity.IceCreamInstance;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer;
import io.github.pbremer.icecreammanager.service.DefaultInventoryService;
import io.github.pbremer.icecreammanager.service.IceCreamService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;

/**
 * @author Patrick Bremer
 */
public class BeginDayInventoryConverter implements
        Converter<LoadTruckFlatFileContainer, TruckInstance>, InitializingBean {

    @Autowired
    private TruckInstanceService truckService;

    @Autowired
    private IceCreamService iceCreamService;

    @Autowired
    private DefaultInventoryService defaultInventoryService;

    @Autowired
    private WarehouseInventoryService warehouseService;

    // @Value("#{jobExecutionContext['day']}")
    private long ms;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public TruckInstance convert(LoadTruckFlatFileContainer source) {
	Date day = new Date(ms);
	TruckInstance truck = truckService.getTruckByDayAndTruckNumber(day,
	        source.getTruckNumber());

	Map<String, DefaultInventory> map = createMap(
	        defaultInventoryService.findWhereIsActiveEquals(true));
	List<BeginDayInventory> inventoryList =
	        new ArrayList<BeginDayInventory>();

	BeginDayInventory beginInventory = new BeginDayInventory();
	IceCreamInstance iceCreamInstance = new IceCreamInstance();

	for (ItemAdjustmentFlatFileContainer flatInventory : source
	        .getItemAdjustments()) {

	    if (map.containsKey(flatInventory.getItemNumber())) {
		iceCreamInstance.setIceCream(
		        map.get(flatInventory.getItemNumber()).getIceCream());
		beginInventory.setAmmount(
		        map.get(flatInventory.getItemNumber()).getAmmount()
		                + Integer.valueOf(
		                        flatInventory.getAdjustmentQuantity()));
		map.remove(flatInventory.getItemNumber());
	    } else {
		iceCreamInstance.setIceCream(
		        iceCreamService.getOne(flatInventory.getItemNumber()));
		beginInventory.setAmmount(
		        Integer.valueOf(flatInventory.getAdjustmentQuantity()));
	    }
	    beginInventory.setPrice(
	            warehouseService.getPrice(flatInventory.getItemNumber()));
	    beginInventory.setDay(day);
	    iceCreamInstance.setDay(day);
	    beginInventory.setIceCreamInstance(iceCreamInstance);
	    beginInventory.setTruckInstance(truck);
	}

	for (String key : map.keySet()) {
	    iceCreamInstance.setIceCream(map.get(key).getIceCream());
	    beginInventory.setAmmount(map.get(key).getAmmount());
	    beginInventory.setIceCreamInstance(iceCreamInstance);
	    beginInventory.setTruckInstance(truck);
	}

	inventoryList.add(beginInventory);

	truck.setBeginDayInventory(inventoryList);

	return truck;
    }

    private Map<String, DefaultInventory>
            createMap(List<DefaultInventory> defaultInventory) {
	Map<String, DefaultInventory> map =
	        new TreeMap<String, DefaultInventory>();
	for (DefaultInventory inventory : defaultInventory) {
	    map.put(inventory.getIceCream().getIceCreamName(), inventory);
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
