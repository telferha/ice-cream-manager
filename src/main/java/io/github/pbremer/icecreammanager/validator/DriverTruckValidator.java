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

import io.github.pbremer.icecreammanager.flatfilecontents.DriverTruckFlatFileContainer;
import io.github.pbremer.icecreammanager.service.DriverService;
import io.github.pbremer.icecreammanager.service.TruckService;

/**
 * @author Patrick Bremer
 */
public class DriverTruckValidator implements Validator, InitializingBean {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverTruckValidator.class);

    private static Vector<String> truckCahce;
    private static Vector<String> driverCache;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverService driverService;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new DriverTruckFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	DriverTruckFlatFileContainer arg =
	        (DriverTruckFlatFileContainer) target;

	logger.debug(target.toString());

	if (truckCahce.contains(arg.getTruckNumber())
	        || !truckService.existsAndIsActive(arg.getTruckNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Truck ").append(arg.getTruckNumber())
	                    .append(" already exists").toString());
	    return;
	}
	if (driverCache.contains(arg.getDriverNumber())
	        || !driverService.existsAndIsActive(arg.getDriverNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Driver ").append(arg.getDriverNumber())
	                    .append(" already exists").toString());
	    return;
	}
	truckCahce.add(arg.getTruckNumber());
	driverCache.add(arg.getDriverNumber());

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
	if (driverCache == null) {
	    driverCache = new Vector<String>();
	}
    }

}
