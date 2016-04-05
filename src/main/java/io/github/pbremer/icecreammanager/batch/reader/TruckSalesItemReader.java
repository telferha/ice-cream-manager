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
		inputFile.setSequenceNumber(line.readShort("Sequence Number"));
		inputFile.setDay(line.readDate("Date"));
	    } else if ("TR".equalsIgnoreCase(prefix)) {
		Assert.notNull(inputFile,
		        "Truck header record encountered before file header record");
		Assert.isNull(truckSales,
		        "Truck header record encountered twice without a closing adjustment row");
		truckSales = new TruckSalesFlatFileContainer();
		truckSales.setTruckNumber(line.readString("Truck Number"));
	    } else if ("SR".equalsIgnoreCase(prefix)) {
		Assert.notNull(inputFile,
		        "Sales row encountered before file header record");
		Assert.notNull(truckSales,
		        "Sales row encountered without a truck header row");
		truckSales
		        .setInventoryRowNumber(line.readString("Sales Count"));
		inputFile.add(truckSales);
		truckSales = null;
	    } else if ("T".equalsIgnoreCase(prefix)) {
		Assert.notNull(inputFile,
		        "Encountered trailer record before the header");
		Assert.isNull(truckSales,
		        "Encountered file trailer without ending the truck sales record");
		inputFile.setFooterNumber(line.readInt("Record Count"));
		return inputFile;
	    } else if (null != prefix && prefix.matches("[0-9]*")) {
		Assert.notNull(truckSales,
		        "Encountered item row without seeing truck number");
		TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer inventory =
		        truckSales.new EndOfDayInventoryFlatFileContainer();
		inventory.setItemNumber(line.readString("Item Number"));
		inventory.setFinalQuantity(line.readString("Final Quantity"));
		truckSales.addInventory(inventory);

	    }
	}
	Assert.isNull(inputFile, "No ending trailer row was found");
	return null;
    }

}
