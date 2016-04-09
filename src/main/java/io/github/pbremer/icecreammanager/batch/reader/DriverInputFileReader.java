/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.DriverFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class DriverInputFileReader
        extends MultilineFlatFileItemReader<DriverFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public DriverFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		DriverFlatFileContainer driver = new DriverFlatFileContainer();
		driver.setDriverNumber(line.readString("Driver Number"));
		driver.setHourlyWage(line.readString("Wage"));
		return driver;
	    }
	}
	return null;
    }

}
