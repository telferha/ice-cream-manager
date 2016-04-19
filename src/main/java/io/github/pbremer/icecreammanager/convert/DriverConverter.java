/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.Driver;
import io.github.pbremer.icecreammanager.flatfilecontents.DriverFlatFileContainer;
import io.github.pbremer.icecreammanager.utils.NumberHelper;

/**
 * @author Patrick Bremer
 */
public class DriverConverter
        implements Converter<DriverFlatFileContainer, Driver> {

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public Driver convert(DriverFlatFileContainer source) {
	Driver driver = new Driver();
	driver.setCurrentWage(NumberHelper
	        .convertPenniesStringToDecimal(source.getHourlyWage()));
	driver.setDriverId(source.getDriverNumber());
	return driver;
    }

}
