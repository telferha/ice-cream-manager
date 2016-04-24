/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.RoutePriceFlatFileContainer;
import io.github.pbremer.icecreammanager.flatfilecontents.RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class RoutePriceInputFileReader
        extends MultilineFlatFileItemReader<RoutePriceFlatFileContainer> {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public RoutePriceFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	RoutePriceFlatFileContainer routePrice = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {
	    String prefix = line.readString(0);

	    if ("R".equalsIgnoreCase(prefix)) {
		Assert.isNull(routePrice,
		        "Encountered route row before the count row");
		routePrice = new RoutePriceFlatFileContainer();
		routePrice.setRouteNumber(line.readString("Route Number"));
	    } else if ("C".equalsIgnoreCase(prefix)) {
		Assert.notNull(routePrice,
		        "Encountered count row before rout row");
		routePrice.setCount(line.readString("Count"));
		return routePrice;
	    } else if (prefix.matches("^[0-9].*")) {
		Assert.notNull(routePrice,
		        "Encountered inventory row before route row");
		ItemPriceAdjustmentFlatFileContainer priceAdjustment =
		        routePrice.new ItemPriceAdjustmentFlatFileContainer();
		priceAdjustment.setItemNumber(line.readString("Item Number"));
		priceAdjustment.setPriceAdjustment(
		        line.readString("Price Adjustment"));
		routePrice.addPriceAdjustment(priceAdjustment);
	    }

	}
	Assert.isNull(routePrice, "No ending count row not found");
	return null;
    }

}
