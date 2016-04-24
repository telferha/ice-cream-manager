/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckValidator implements Validator, InitializingBean {

    private static List<String> truckCahce;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new TruckFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	TruckFlatFileContainer arg = (TruckFlatFileContainer) target;
	if (truckCahce.contains(arg.getTruckNumber())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Truck ").append(arg.getTruckNumber())
	                    .append(" already exists").toString());
	    return;
	}
	truckCahce.add(arg.getTruckNumber());

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
    }

}
