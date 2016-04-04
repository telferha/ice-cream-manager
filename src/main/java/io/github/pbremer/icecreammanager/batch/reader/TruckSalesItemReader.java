/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.InputFileContents;
import io.github.pbremer.icecreammanager.flatfilecontents.TruckSalesFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckSalesItemReader extends
        MultilineFlatFileItemReader<InputFileContents<TruckSalesFlatFileContainer>> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public InputFileContents<TruckSalesFlatFileContainer> read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {
	InputFileContents<TruckSalesFlatFileContainer> inputFile = null;
	TruckSalesFlatFileContainer truckSales = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if ("HD".equalsIgnoreCase(prefix)) {
		Assert.isNull(inputFile, "Header file encountered twice");
		inputFile =
		        new InputFileContents<TruckSalesFlatFileContainer>();
	    } else if ("TR".equalsIgnoreCase(prefix)) {
		Assert.isNull(truckSales,
		        "Truck header record encountered twice without a closing adjustment row");
		truckSales = new TruckSalesFlatFileContainer();
		truckSales.setTruckNumber(line.readString("Truck Number"));
	    } else if ("SR".equalsIgnoreCase(prefix)) {
		Assert.notNull(truckSales,
		        "Closing adjustment row encountered without a truck header row");
		// TODO (Patrick Bremer): fix flat file entity classes....
		inputFile.add(truckSales);
		truckSales = null;
	    } else if ("T".equalsIgnoreCase(prefix)) {
		Assert.notNull(inputFile,
		        "Encountered trailer record before the header");
		Assert.isNull(truckSales,
		        "Encountered file trailer without ending the truck sales record");
		return inputFile;
	    } else if (null != prefix && prefix.matches("[0-9]*")) {
		Assert.notNull(truckSales,
		        "Encountered item row without seeing truck number");
		// TODO (Patrick Bremer): fix flat file entity classes...

	    }
	}
	Assert.isNull(inputFile, "No ending trailer row was found");
	return null;
    }

}
