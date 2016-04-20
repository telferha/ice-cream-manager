/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.Date;

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
        implements Converter<DriverTruckFlatFileContainer, TruckInstance> {

    @Autowired
    private TruckInstanceService truckService;

    @Autowired
    private DriverService driverService;

    private Date day;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public TruckInstance convert(DriverTruckFlatFileContainer source) {
	TruckInstance truck = null;// = truckService.getTruckByDayAndTruckNumber(day,
	        //source.getTruckNumber());
	DriverInstance driver = new DriverInstance();
	driver.setDriver(driverService.getOne(source.getDriverNumber()));
	driver.setWage(driver.getDriver().getCurrentWage());
	driver.setDay(day);
	truck.setDriverInstance(driver);
	return truck;
    }

    public void setDay(Date day) {
	this.day = day;
    }

}
