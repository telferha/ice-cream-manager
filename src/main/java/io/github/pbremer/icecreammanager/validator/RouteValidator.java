/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.RouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class RouteValidator implements Validator {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ZoneService zoneService;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(RouteFlatFileContainer.class);
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
	} else if ("D".equalsIgnoreCase(arg.getActionCode())) {
	    if (!arg.getCityLabel().isEmpty()) {
		errors.reject("route.actioncode.delete",
		        "Action code 'D' must not have any zones listed");
	    } else if (!routeService.existsAndIsActive(arg.getRouteNumber())) {
		errors.reject("route.routenumber.dne", String.format(
		        "Route \"%s\" already exists", arg.getRouteNumber()));
	    }
	} else {
	    if (arg.getCityLabel().isEmpty()) {
		errors.reject("route.actioncode.add-create",
		        "Action code 'A' or 'C' must not have at least one zone listed");
	    } else if ("A".equalsIgnoreCase(arg.getActionCode())
	            && !routeService.existsAndIsActive(arg.getRouteNumber())) {
		errors.reject("route.actioncode.add.dne", String.format(
		        "Route \"%s\" does not exist", arg.getRouteNumber()));
	    } else if ("C".equalsIgnoreCase(arg.getActionCode())
	            && routeService.existsAndIsActive(arg.getRouteNumber())) {
		errors.reject("route.routenumber.dne", String.format(
		        "Route \"%s\" already exists", arg.getRouteNumber()));
	    } else {
		for (String zone : arg.getCityLabel()) {
		    if (!zoneService.existsAndIsActive(zone)) {
			errors.reject("route.citylabel.dne", String.format(
			        "City label \"%s\" does not exist", zone));
			break;
		    }
		}
	    }
	}
    }

}
