/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.DriverFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class DriverValidator implements Validator, InitializingBean {

    private static List<String> driverCahce;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new DriverFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	DriverFlatFileContainer arg = (DriverFlatFileContainer) target;
	if (driverCahce.contains(arg.getDriverNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Driver ").append(arg.getDriverNumber())
	                    .append(" already exists").toString());
	    return;
	}
	driverCahce.add(arg.getDriverNumber());

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	if (driverCahce == null) {
	    driverCahce = new Vector<String>();
	}
    }

}
