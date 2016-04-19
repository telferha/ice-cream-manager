package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.CityFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class CityInputFileReader
        extends MultilineFlatFileItemReader<CityFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public CityFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		CityFlatFileContainer city = new CityFlatFileContainer();
		city.setCityLabel(line.readString("City Label"));
		city.setCityName(line.readString("City Name"));
		city.setState(line.readString("State"));
		return city;
	    }
	}
	return null;
    }

}
