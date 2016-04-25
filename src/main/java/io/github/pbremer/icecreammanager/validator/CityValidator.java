/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.CityFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class CityValidator implements Validator, InitializingBean {

    private List<String> zoneCahce;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(new CityFlatFileContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	CityFlatFileContainer arg = (CityFlatFileContainer) target;
	if (zoneCahce.contains(arg.getCityLabel())) {
	    errors.reject("truck.trucknumber.occupied",
	            new StringBuffer("Truck ").append(arg.getCityLabel())
	                    .append(" already exists").toString());
	    return;
	}
	zoneCahce.add(arg.getCityLabel());

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	if (zoneCahce == null) {
	    zoneCahce = new Vector<String>();
	}
    }

}
