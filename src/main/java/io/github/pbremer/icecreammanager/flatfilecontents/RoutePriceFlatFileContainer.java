/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class RoutePriceFlatFileContainer extends AbstractFlatFileContainer {

    private String routeNumber;
    private List<RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer> adjustmentPrices;
    private String count;

    public RoutePriceFlatFileContainer() {
	adjustmentPrices =
	        new ArrayList<RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer>();
    }

    /**
     * @return the routeNumber
     */
    public String getRouteNumber() {
	return routeNumber;
    }

    /**
     * @param routeNumber
     *            the routeNumber to set
     */
    public void setRouteNumber(String routeNumber) {
	this.routeNumber = routeNumber;
    }

    /**
     * @return the adjustmentPrices
     */
    public List<RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer>
            getAdjustmentPrices() {
	return adjustmentPrices;
    }

    /**
     * @param adjustmentPrices
     *            the adjustmentPrices to set
     */
    public void setAdjustmentPrices(
            List<RoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer> adjustmentPrices) {
	this.adjustmentPrices = adjustmentPrices;
    }

    public void addPriceAdjustment(
            ItemPriceAdjustmentFlatFileContainer adjustment) {
	adjustmentPrices.add(adjustment);
    }

    /**
     * @return the count
     */
    public String getCount() {
	return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
	this.count = count;
    }

    @Override
    public String toString() {
	return ToStringBuilder
	        .reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
	        .toString();
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    public class ItemPriceAdjustmentFlatFileContainer
            extends AbstractFlatFileContainer {

	private String itemNumber;
	private String priceAdjustment;

	/**
	 * @return the itemNumber
	 */
	public String getItemNumber() {
	    return itemNumber;
	}

	/**
	 * @param itemNumber
	 *            the itemNumber to set
	 */
	public void setItemNumber(String itemNumber) {
	    this.itemNumber = itemNumber;
	}

	/**
	 * @return the priceAdjustment
	 */
	public String getPriceAdjustment() {
	    return priceAdjustment;
	}

	/**
	 * @param priceAdjustment
	 *            the priceAdjustment to set
	 */
	public void setPriceAdjustment(String priceAdjustment) {
	    this.priceAdjustment = priceAdjustment;
	}

	@Override
	public String toString() {
	    return ToStringBuilder
	            .reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
	            .toString();
	}

	@Override
	public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals(Object obj) {
	    return EqualsBuilder.reflectionEquals(this, obj, false);
	}

    }
}
