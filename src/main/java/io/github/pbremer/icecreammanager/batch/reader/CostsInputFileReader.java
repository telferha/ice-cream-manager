/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.CostsFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.CostsFlatFileContainer.InventoryLossFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class CostsInputFileReader
        extends MultilineFlatFileItemReader<CostsFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public CostsFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	CostsFlatFileContainer costs = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if ("TR".equalsIgnoreCase(prefix)) {
		Assert.isNull(costs,
		        "Encountered truck row before the count row");
		costs = new CostsFlatFileContainer();
		costs.setTruckNumber(line.readString("Truck Number"));
		costs.setHoursOut(line.readString("Hours Out"));
		costs.setGasSpent(line.readString("Gas Cost"));
	    } else if ("SR".equalsIgnoreCase(prefix)) {
		Assert.notNull(costs, "Encountered count row before truck row");
		costs.setLostCount(line.readString("Count"));
		return costs;
	    } else if (prefix.matches("^[0-9].*")) {
		Assert.notNull(costs,
		        "Encountered inventory row before truck row");
		InventoryLossFlatFileContainer loss =
		        costs.new InventoryLossFlatFileContainer();
		loss.setItemNumber(line.readString("Item Number"));
		loss.setQuantityLost(line.readString("Amount Lost"));
		costs.addLoss(loss);
	    }

	}
	Assert.isNull(costs, "No ending count row not found");
	return null;
    }

}
