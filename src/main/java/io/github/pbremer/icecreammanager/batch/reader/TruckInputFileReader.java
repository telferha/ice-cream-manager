/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckInputFileReader
        extends MultilineFlatFileItemReader<TruckFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public TruckFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		TruckFlatFileContainer truck = new TruckFlatFileContainer();
		truck.setTruckNumber(line.readString("Truck Number"));
		return truck;
	    }
	}

	return null;
    }
}
