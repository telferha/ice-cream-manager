/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.pbremer.icecreammanager.flatfilecontents.LoadInventoryFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class LoadInventoryInputFileReader
        extends MultilineFlatFileItemReader<LoadInventoryFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public LoadInventoryFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readRawString(0);

	    if (prefix.length() > 1) {
		LoadInventoryFlatFileContainer inventory =
		        new LoadInventoryFlatFileContainer();
		inventory.setItemNumber(line.readString("Item Number"));
		inventory.setWareHouseQuantity(
		        line.readString("Warehouse Quantity"));
		inventory.setPrice(line.readString("Price"));
		inventory.setDescription(line.readString("Description"));
		return inventory;
	    }

	}

	return null;
    }

}
