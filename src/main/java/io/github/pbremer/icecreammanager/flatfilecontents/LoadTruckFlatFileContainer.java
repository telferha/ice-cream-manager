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
public class LoadTruckFlatFileContainer extends AbstractFlatFileContainer {

    private String truckNumber;
    private List<LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer> itemAdjustments;
    private String adjustmentRowNumber;

    public LoadTruckFlatFileContainer() {
	itemAdjustments =
	        new ArrayList<LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer>();
    }

    /**
     * @return the truckNumber
     */
    public String getTruckNumber() {
	return truckNumber;
    }

    /**
     * @param truckNumber
     *            the truckNumber to set
     */
    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    /**
     * @return the itemAdjustments
     */
    public List<LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer>
            getItemAdjustments() {
	return itemAdjustments;
    }

    /**
     * @param itemAdjustments
     *            the itemAdjustments to set
     */
    public void setItemAdjustments(
            List<LoadTruckFlatFileContainer.ItemAdjustmentFlatFileContainer> itemAdjustments) {
	this.itemAdjustments = itemAdjustments;
    }

    public void addAdjustment(ItemAdjustmentFlatFileContainer adjustment) {
	itemAdjustments.add(adjustment);
    }

    /**
     * @return the adjustmentRowNumber
     */
    public String getAdjustmentRowNumber() {
	return adjustmentRowNumber;
    }

    /**
     * @param adjustmentRowNumber
     *            the adjustmentRowNumber to set
     */
    public void setAdjustmentRowNumber(String adjustmentRowNumber) {
	this.adjustmentRowNumber = adjustmentRowNumber;
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

    public class ItemAdjustmentFlatFileContainer
            extends AbstractFlatFileContainer {

	private String itemNumber;
	private String adjustmentQuantity;

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
	 * @return the adjustmentQuantity
	 */
	public String getAdjustmentQuantity() {
	    return adjustmentQuantity;
	}

	/**
	 * @param adjustmentQuantity
	 *            the adjustmentQuantity to set
	 */
	public void setAdjustmentQuantity(String adjustmentQuantity) {
	    this.adjustmentQuantity = adjustmentQuantity;
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
