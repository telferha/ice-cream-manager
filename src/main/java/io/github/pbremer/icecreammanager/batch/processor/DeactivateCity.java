/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.Route;
import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;
import io.github.pbremer.icecreammanager.service.CityService;
import io.github.pbremer.icecreammanager.service.RouteService;

/**
 * @author Patrick Bremer
 */
public class DeactivateCity implements
        ItemProcessor<AbstractFlatFileContainer, AbstractFlatFileContainer> {

    @Autowired
    private CityService city;

    @Autowired
    private RouteService routeService;

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
	    route.getZones().clear();
	    routeService.save(route);
	}
	routeService.setAllIsActiveFromTo(true, false);
	return item;
    }

}
