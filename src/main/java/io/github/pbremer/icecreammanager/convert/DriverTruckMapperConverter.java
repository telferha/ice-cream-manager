/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.DriverInstance;
import io.github.pbremer.icecreammanager.entity.TruckInstance;
import io.github.pbremer.icecreammanager.flatfilecontents.DriverTruckFlatFileContainer;
import io.github.pbremer.icecreammanager.service.DriverService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;

/**
 * @author Patrick Bremer
 */
public class DriverTruckMapperConverter
        implements Converter<DriverTruckFlatFileContainer, DriverInstance>,
        InitializingBean {

    @Autowired
    private TruckInstanceService truckService;

    @Autowired
    private DriverService driverService;

    private long ms;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public DriverInstance convert(DriverTruckFlatFileContainer source) {
	Date day = new Date(ms);
	TruckInstance truck = truckService.getTruckByDayAndTruckNumber(day,
	        source.getTruckNumber());
	DriverInstance driver = new DriverInstance();
	driver.setDriver(driverService.getOne(source.getDriverNumber()));
	driver.setWage(driver.getDriver().getCurrentWage());
	driver.setDay(day);
	driver.setTruckInstance(truck);
	return driver;
    }

    public void setMs(long ms) {
	this.ms = ms;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	// Assert.notNull(day, "Day must be set");
    }

}
