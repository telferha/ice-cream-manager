/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class LoadTruckInputFileReader
        extends MultilineFlatFileItemReader<LoadTruckFlatFileContainer> {

    private static final Logger log =
            LoggerFactory.getLogger(LoadTruckFlatFileContainer.class);

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public LoadTruckFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	LoadTruckFlatFileContainer loadTruck = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if ("TR".equalsIgnoreCase(prefix)) {
		Assert.isNull(loadTruck,
		        "Truck header record encountered twice without a closing adjustment row");
		loadTruck = new LoadTruckFlatFileContainer();
		loadTruck.setTruckNumber(line.readString("Truck Number"));
	    } else if ("IR".equalsIgnoreCase(prefix)) {
		Assert.notNull(loadTruck,
		        "Adjustment count row encountered without a truck header row");
		loadTruck.setAdjustmentRowNumber(
		        line.readString("Adjustment Number"));
		return loadTruck;
	    } else if (prefix.matches("^[0-9]+")) {
		log.debug(line.toString());
		Assert.notNull(loadTruck,
		        "Encountered item row without seeing truck number");
		ItemAdjustmentFlatFileContainer inventoryAdjustment =
		        loadTruck.new ItemAdjustmentFlatFileContainer();
		inventoryAdjustment
		        .setItemNumber(line.readString("Item Number"));
		inventoryAdjustment.setAdjustmentQuantity(
		        line.readString("Adjustment Quantity"));
		loadTruck.addAdjustment(inventoryAdjustment);

	    }
	}
	Assert.isNull(loadTruck, "No ending adjustment count row was found");
	return null;
    }

}
