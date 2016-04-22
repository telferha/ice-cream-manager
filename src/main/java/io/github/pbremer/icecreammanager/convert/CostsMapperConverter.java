/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import static io.github.pbremer.icecreammanager.utils.NumberHelper.convertPenniesStringToDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.entity.InventoryLoss;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.CostsFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.CostsFlatFileContainer.InventoryLossFlatFileContainer;
import io.github.pbremer.icecreammanager.service.BeginDayInventoryService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
public class CostsMapperConverter implements
        Converter<CostsFlatFileContainer, TruckInstance>, InitializingBean {

    @Autowired
    private BeginDayInventoryService inventoryService;

    @Autowired
    private TruckInstanceService truckService;

    @Value("#{new Date(#{jobExecutionContext['day']})}")
    private Date day;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public TruckInstance convert(CostsFlatFileContainer source) {
	TruckInstance truck =
	        truckService.getOne(Long.valueOf(source.getTruckNumber()));
	List<BeginDayInventory> beginInventoryList =
	        inventoryService.findByTruckInstance(truck);
	Map<String, Integer> map = createMap(source.getLostInventory());
	List<InventoryLoss> inventoryList = new ArrayList<InventoryLoss>();

	InventoryLoss lostInventory;
	for (BeginDayInventory beginInventory : beginInventoryList) {

	    if (map.containsKey(beginInventory.getIceCreamInstance()
	            .getIceCream().getIceCreamName())) {

		lostInventory = new InventoryLoss();
		lostInventory.setIceCreamInstance(
		        beginInventory.getIceCreamInstance());
		lostInventory
		        .setAmmount(map.get(beginInventory.getIceCreamInstance()
		                .getIceCream().getIceCreamName()));
		lostInventory.setPrice(beginInventory.getPrice());
		inventoryList.add(lostInventory);
	    }
	}

	truck.setInventoryLoss(inventoryList);
	truck.setHoursOut(convertPenniesStringToDecimal(source.getHoursOut()));
	truck.setGasSpent(convertPenniesStringToDecimal(source.getGasSpent()));
	return truck;
    }

    private Map<String, Integer>
            createMap(List<InventoryLossFlatFileContainer> inventoryLoss) {
	Map<String, Integer> map = new TreeMap<String, Integer>();

	for (InventoryLossFlatFileContainer inventory : inventoryLoss) {
	    map.put(inventory.getItemNumber(),
	            Integer.valueOf(inventory.getQuantityLost()));
	}

	return map;
    }

    public void setDay(Date day) {
	this.day = day;
    }

    public void setDay(long ms) {
	this.day = new Date(ms);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(day, "Day must be set");
    }

}
