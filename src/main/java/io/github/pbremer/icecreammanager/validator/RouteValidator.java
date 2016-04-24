/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.RouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class RouteValidator implements Validator, InitializingBean {

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
	    deleteRoute(arg, errors);
	} else if ("A".equalsIgnoreCase(arg.getActionCode())) {
	    addToRoute(arg, errors);
	} else {
	    createRoute(arg, errors);
	}
    }

    private void deleteRoute(RouteFlatFileContainer arg, Errors errors) {
	if (!arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.delete",
	            "Deleting a route must not have any zones listed");
	    return;
	}
	if (!routeService.existsAndIsActive(arg.getRouteNumber())) {
	    errors.reject("route.routenumber.dne", "Route cannot be removed");
	    return;
	}
	for (String zone : arg.getCityLabel()) {
	    if (!isZoneAvailable(zone)) {
		errors.reject("route.citylabel.dne", String
		        .format("City label \"%s\" cannot be removed", zone));
		return;
	    }
	}
	cacheRoute("available", arg.getRouteNumber());
	cacheZone("available", arg.getCityLabel());
    }

    private void createRoute(RouteFlatFileContainer arg, Errors errors) {
	if (arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.add-create",
	            "Route must have at least one zone to be created");
	    return;
	}
	if (isRouteAvailable(arg.getRouteNumber())) {
	    errors.reject("route.routenumber.dne", "Route already exists");
	    return;
	}
	for (String zone : arg.getCityLabel()) {
	    if (!isZoneAvailable(zone)) {
		errors.reject("route.zonenumber.dne",
		        String.format("City Label \"%s\" cannot be added",
		                arg.getRouteNumber()));
		return;
	    }
	}
	cacheRoute("occupied", arg.getRouteNumber());
	occupyZones(arg.getCityLabel());

    }

    private void addToRoute(RouteFlatFileContainer arg, Errors errors) {
	if (arg.getCityLabel().isEmpty()) {
	    errors.reject("route.actioncode.add-create",
	            "At least one zone must be added");
	    return;
	}
	if (!isRouteAvailable(arg.getRouteNumber())) {
	    errors.reject("route.actioncode.add.dne", "Route not available");
	    return;
	}
	if (routeService.countZones(arg.getRouteNumber()) > 10) {
	    errors.reject("route.actioncode.add.zonecount",
	            "A route can only have at most 10 zones");
	    return;
	}
	occupyZones(arg.getCityLabel());
    }

    private boolean isRouteAvailable(String routeNumber) {
	return zonesCache.get("available").contains(routeNumber)
	        || !zoneService.existsAndIsActive(routeNumber);
    }

    private boolean isZoneAvailable(String zone) {
	return zonesCache.get("available").contains(zone)
	        || !zoneService.existsAndIsActive(zone);
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

	zonesCache = new HashMap<String, List<String>>();
	zonesCache.put("occupied", new Vector<String>());
	zonesCache.put("available", new Vector<String>());

	routesCache = new HashMap<String, List<String>>();
	routesCache.put("occupied", new Vector<String>());
	routesCache.put("available", new Vector<String>());
    }

}
