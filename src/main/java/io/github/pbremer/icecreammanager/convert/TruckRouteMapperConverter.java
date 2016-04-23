/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.Date;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.RouteInstance;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.TruckRouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.TruckService;

/**
 * @author Patrick Bremer
 */
@JobScope
public class TruckRouteMapperConverter
        implements Converter<TruckRouteFlatFileContainer, TruckInstance> {

    @Autowired
    private TruckService truckService;

    @Autowired
    private RouteService routeService;

    private long ms;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public TruckInstance convert(TruckRouteFlatFileContainer source) {
	Date day = new Date(ms);
	TruckInstance truck = new TruckInstance();
	RouteInstance route = new RouteInstance();
	truck.setTruck(truckService.getOne(source.getRouteNumber()));
	truck.setDay(day);
	route.setRoute(routeService.getOne(source.getRouteNumber()));
	route.setDay(day);
	truck.setRouteInstance(route);
	route.setTruckInstance(truck);

	return truck;
    }

    public void setMs(long ms) {
	this.ms = ms;
    }

}
