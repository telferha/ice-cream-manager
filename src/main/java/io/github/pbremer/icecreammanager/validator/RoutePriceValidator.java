/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.RoutePriceFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;

/**
 * @author Patrick Bremer
 */
public class RoutePriceValidator implements Validator {

    @Autowired
    private RouteService routeService;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(RoutePriceFlatFileContainer.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	RoutePriceFlatFileContainer arg = (RoutePriceFlatFileContainer) target;
	if (!routeService.existsAndIsActive(arg.getRouteNumber())) {
	    errors.reject("route.citylabel.dne", String.format(
	            "City label: \"%s\" does not exist", arg.getRouteNumber()));
	}
    }

}
