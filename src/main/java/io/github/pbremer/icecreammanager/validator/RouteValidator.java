/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.flatfilecontents.RouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class RouteValidator implements Validator, InitializingBean {

    private static final Logger logger =
            LoggerFactory.getLogger(RouteValidator.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private ZoneService zoneService;

    private Map<String, List<String>> zonesCache;
    private Map<String, List<String>> routesCache;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new RouteFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	RouteFlatFileContainer arg = (RouteFlatFileContainer) target;
	if (!arg.getActionCode().matches("[AaCcDd]")) {
	    errors.reject("route.actioncode.not-defined",
	            "Action code must be either 'A', 'C', or 'D'");
	    return;
	}
	if ("D".equalsIgnoreCase(arg.getActionCode())) {
	    logger.info("Delete {}", arg.getRouteNumber());
	    deleteRoute(arg, errors);
	} else if ("C".equalsIgnoreCase(arg.getActionCode())) {
	    logger.info("Change {}", arg.getRouteNumber());
	    addToRoute(arg, errors);
	} else {
	    logger.info("Create {}", arg.getRouteNumber());
	    createRoute(arg, errors);
	}
    }

    private void deleteRoute(RouteFlatFileContainer arg, Errors errors) {
	if (!arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.delete",
	            String.format(
	                    "Deleting route \"%s\" must not have any zones listed",
	                    arg.getRouteNumber()));
	    return;
	}
	if (!routeService.existsAndIsActive(arg.getRouteNumber())) {
	    errors.reject("route.routenumber.dne", String.format(
	            "Route \"%s\" cannot be removed", arg.getRouteNumber()));
	    return;
	}

	List<String> availableZones = new ArrayList<String>();
	for (Zone zone : routeService.getOne(arg.getRouteNumber()).getZones()) {
	    availableZones.add(zone.getZoneName());
	}
	cacheRoute("available", arg.getRouteNumber());
	cacheZone("available", availableZones);
    }

    private void createRoute(RouteFlatFileContainer arg, Errors errors) {
	if (arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.add-create",
	            String.format(
	                    "Route \"%s\" must have at least one zone to be created",
	                    arg.getRouteNumber()));
	    return;
	}
	if (!isRouteAvailable(arg.getRouteNumber())) {
	    errors.reject("route.routenumber.dne", String.format(
	            "Route \"%s\" already exists", arg.getRouteNumber()));
	    return;
	}
	for (String zone : arg.getCityLabel()) {
	    if (!isZoneAvailable(zone)) {
		errors.reject("route.zonenumber.dne",
		        String.format(
		                "City label \"%s\" cannot be added to route \"%s\"",
		                zone, arg.getRouteNumber()));
		return;
	    }
	}
	cacheRoute("occupied", arg.getRouteNumber());
	occupyZones(arg.getCityLabel());

    }

    private void addToRoute(RouteFlatFileContainer arg, Errors errors) {
	if (arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.add-create",
	            String.format(
	                    "At least one zone must be added for route \"%s\"",
	                    arg.getRouteNumber()));
	    return;
	}
	if (routesCache.get("available").contains(arg.getRouteNumber())
	        || !routeService.existsAndIsActive(arg.getRouteNumber())) {
	    errors.reject("route.actioncode.add.dne",
	            String.format(
	                    "Route \"%s\" not available for appending routes",
	                    arg.getRouteNumber()));
	    return;
	}
	int zCount = routeService.countZones(arg.getRouteNumber());
	if (zCount + arg.getCityLabel().size() > 10) {
	    errors.reject("route.actioncode.add.zonecount", String.format(
	            "Route \"%s\" cannot add %d more City Labels because there are already %d assigned",
	            arg.getRouteNumber(), arg.getCityLabel().size(), zCount));
	    return;
	}
	for (String zone : arg.getCityLabel()) {
	    if (!isZoneAvailable(zone)) {
		errors.reject("route.zonenumber.dne",
		        String.format(
		                "City label \"%s\" cannot be added to route \"%s\"",
		                zone, arg.getRouteNumber()));
		return;
	    }
	}
	occupyZones(arg.getCityLabel());
    }

    private boolean isRouteAvailable(String routeNumber) {
	return (routesCache.get("available").contains(routeNumber)
	        && !routesCache.get("occupied").contains(routeNumber))
	        || !routeService.existsAndIsActive(routeNumber);
    }

    private boolean isZoneAvailable(String zone) {
	return (zonesCache.get("available").contains(zone)
	        && !zonesCache.get("occupied").contains(zone))
	        || zoneService.existsAndIsActive(zone);
    }

    private void cacheRoute(String status, String routeNumber) {
	routesCache.get(status).add(routeNumber);
    }

    private void cacheZone(String status, List<String> zone) {
	zonesCache.get(status).addAll(zone);
    }

    private void occupyZones(List<String> zones) {
	List<String> availableZones = zonesCache.get("available");
	for (String zone : zones) {
	    availableZones.remove(zone);
	}
	cacheZone("occupied", zones);

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(routeService, "routeService must be set");
	Assert.notNull(zoneService, "zoneService must be set");

	if (zonesCache == null) {
	    zonesCache = new ConcurrentHashMap<String, List<String>>();
	    zonesCache.put("occupied", new Vector<String>());
	    zonesCache.put("available", new Vector<String>());
	}
	if (routesCache == null) {
	    routesCache = new ConcurrentHashMap<String, List<String>>();
	    routesCache.put("occupied", new Vector<String>());
	    routesCache.put("available", new Vector<String>());
	}
    }

}
