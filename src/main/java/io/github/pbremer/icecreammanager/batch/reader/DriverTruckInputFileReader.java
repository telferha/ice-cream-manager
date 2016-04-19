/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.DriverTruckFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class DriverTruckInputFileReader
        extends MultilineFlatFileItemReader<DriverTruckFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public DriverTruckFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		DriverTruckFlatFileContainer driverTruck =
		        new DriverTruckFlatFileContainer();
		driverTruck.setDriverNumber(line.readString("Driver Number"));
		driverTruck.setTruckNumber(line.readString("Truck Number"));
		return driverTruck;
	    }
	}
	return null;
    }

}
