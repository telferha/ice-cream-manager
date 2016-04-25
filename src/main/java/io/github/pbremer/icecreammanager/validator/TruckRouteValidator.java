/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckRouteFlatFileContainer;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.TruckService;

/**
 * @author Patrick Bremer
 */
public class TruckRouteValidator implements Validator, InitializingBean {

    private static final Logger logger =
            LoggerFactory.getLogger(TruckRouteValidator.class);

    private static Vector<String> truckCahce;
    private static Vector<String> routeCache;

    @Autowired
    private TruckService truckService;

    @Autowired
    private RouteService routeService;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new TruckRouteFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	TruckRouteFlatFileContainer arg = (TruckRouteFlatFileContainer) target;
	logger.debug(arg.toString());

	if (truckCahce.contains(arg.getTruckNumber())
	        || !truckService.existsAndIsActive(arg.getTruckNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Truck ").append(arg.getTruckNumber())
	                    .append(" already exists").toString());
	    return;
	}
	if (routeCache.contains(arg.getTruckNumber())
	        || !routeService.existsAndIsActive(arg.getRouteNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Route ").append(arg.getRouteNumber())
	                    .append(" already exists").toString());
	    return;
	}
	truckCahce.add(arg.getTruckNumber());
	routeCache.add(arg.getRouteNumber());

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	if (truckCahce == null) {
	    truckCahce = new Vector<String>();
	}
	if (routeCache == null) {
	    routeCache = new Vector<String>();
	}
    }

}
