/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckSalesFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckSalesItemReader
        extends MultilineFlatFileItemReader<TruckSalesFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public TruckSalesFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {
	TruckSalesFlatFileContainer truckSales = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if ("TR".equalsIgnoreCase(prefix)) {
		Assert.isNull(truckSales,
		        "Truck header record encountered twice without a closing adjustment row");
		truckSales = new TruckSalesFlatFileContainer();
		truckSales.setTruckNumber(line.readString("Truck Number"));
	    } else if ("SR".equalsIgnoreCase(prefix)) {
		Assert.notNull(truckSales,
		        "Sales row encountered without a truck header row");
		truckSales
		        .setInventoryRowNumber(line.readString("Sales Count"));
		return truckSales;
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
	Assert.isNull(truckSales, "No ending sales row was found");
	return null;
    }

}
