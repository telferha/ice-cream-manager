/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.RouteFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class RouteInputFileReader
        extends MultilineFlatFileItemReader<RouteFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public RouteFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if (prefix.matches("^[ACD]")) {
		RouteFlatFileContainer route = new RouteFlatFileContainer();
		route.setActionCode(line.readString("Action Code"));
		List<String> cities = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
		    String city =
		            line.readString(String.format("City Label %d", i));
		    if (!city.isEmpty()) {
			cities.add(city);
		    }
		}
		route.setCityLabel(cities);
		route.setRouteNumber(line.readString("Route Number"));
		return route;
	    }
	}
	return null;
    }
}
