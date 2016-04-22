/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckRouteFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckRouteInputFileReader
        extends MultilineFlatFileItemReader<TruckRouteFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public TruckRouteFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		TruckRouteFlatFileContainer truckRoute =
		        new TruckRouteFlatFileContainer();
		truckRoute.setTruckNumber(line.readString("Truck Number"));
		truckRoute.setRouteNumber(line.readString("Route Number"));
		return truckRoute;
	    }
	}

	return null;
    }

}
