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
public class LoadRoutePriceFlatFileContainer extends AbstractFlatFileContainer {

    private String routeNumber;
    private List<LoadRoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer> priceAdjustments;
    private String itemsCount;

    public LoadRoutePriceFlatFileContainer() {
	priceAdjustments =
	        new ArrayList<LoadRoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer>();
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
     * @return the priceAdjustments
     */
    public List<LoadRoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer>
            getPriceAdjustments() {
	return priceAdjustments;
    }

    /**
     * @param priceAdjustments
     *            the priceAdjustments to set
     */
    public void setPriceAdjustments(
            List<LoadRoutePriceFlatFileContainer.ItemPriceAdjustmentFlatFileContainer> priceAdjustments) {
	this.priceAdjustments = priceAdjustments;
    }

    public void addPriceAdjustment(
            ItemPriceAdjustmentFlatFileContainer adjustment) {
	priceAdjustments.add(adjustment);
    }

    /**
     * @return the itemsCount
     */
    public String getItemsCount() {
	return itemsCount;
    }

    /**
     * @param itemsCount
     *            the itemsCount to set
     */
    public void setItemsCount(String itemsCount) {
	this.itemsCount = itemsCount;
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
