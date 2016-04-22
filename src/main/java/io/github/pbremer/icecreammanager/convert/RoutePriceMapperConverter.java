/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import static io.github.pbremer.icecreammanager.utils.NumberHelper.convertPenniesStringToDecimal;

import java.math.BigDecimal;
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
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.RoutePriceFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer;
import io.github.pbremer.icecreammanager.service.BeginDayInventoryService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
public class RoutePriceMapperConverter
        implements Converter<RoutePriceFlatFileContainer, TruckInstance>,
        InitializingBean {

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
    public TruckInstance convert(RoutePriceFlatFileContainer source) {
	TruckInstance truck = truckService.getTruckByDayAndRouteNumber(day,
	        source.getRouteNumber());

	Map<String, BigDecimal> map = createMap(source.getAdjustmentPrices());
	truck.setBeginDayInventory(inventoryService.findByTruckInstance(truck));

	for (BeginDayInventory inventory : truck.getBeginDayInventory()) {
	    inventory.setPrice(inventory.getPrice().add(map.get(inventory
	            .getIceCreamInstance().getIceCream().getIceCreamName())));
	}

	return truck;
    }

    public void setDay(Date day) {
	this.day = day;
    }

    public void setDay(long ms) {
	this.day = new Date(ms);
    }

    private Map<String, BigDecimal> createMap(
            List<ItemPriceAdjustmentFlatFileContainer> adjustmentPrices) {

	Map<String, BigDecimal> map = new TreeMap<String, BigDecimal>();

	for (ItemPriceAdjustmentFlatFileContainer item : adjustmentPrices) {
	    map.put(item.getItemNumber(),
	            convertPenniesStringToDecimal(item.getPriceAdjustment()));
	}

	return map;
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
