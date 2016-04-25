/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.Route;
import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;
import io.github.pbremer.icecreammanager.service.CityService;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class DeactivateCity implements
        ItemProcessor<AbstractFlatFileContainer, AbstractFlatFileContainer> {

    @Autowired
    private CityService city;

    @Autowired
    private RouteService routeService;

    @Autowired
    private ZoneService zoneService;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public AbstractFlatFileContainer process(AbstractFlatFileContainer item)
            throws Exception {
	city.setAllIsActiveFromTo(true, false);
	for (Route route : routeService.findWhereIsActiveEquals(true)) {
	    route.setActive(false);
	    routeService.save(route);
	    for (Zone zone : route.getZones()) {
		zone.setActive(false);
		zoneService.save(zone);
	    }
	}
	return item;
    }

}
