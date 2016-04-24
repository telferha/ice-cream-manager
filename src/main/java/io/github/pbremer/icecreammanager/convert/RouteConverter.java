/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.Route;
import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.flatfilecontents.RouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class RouteConverter
        implements Converter<RouteFlatFileContainer, Route> {

    private static final Logger logger =
            LoggerFactory.getLogger(RouteConverter.class);

    @Autowired
    private ZoneService service;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public Route convert(RouteFlatFileContainer source) {
	Route route = new Route();
	route.setRouteId(source.getRouteNumber());
	route.setActive(!"D".equalsIgnoreCase(source.getActionCode()));
	if (route.isActive()) {
	    List<Zone> zones = new ArrayList<Zone>();
	    for (String zoneNum : source.getCityLabel()) {
		Zone zone = service.getOne(zoneNum);
		zones.add(zone);
	    }
	    route.setZones(zones);
	}
	logger.info("{} : {}", route.getRouteId(), route.isActive());
	return route;
    }

}
