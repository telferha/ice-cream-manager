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
public class TruckSalesFlatFileContainer extends AbstractFlatFileContainer {

    private String truckNumber;
    private List<TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer> endOfDayInventory;
    private String inventoryRowNumber;

    public TruckSalesFlatFileContainer() {
	endOfDayInventory =
	        new ArrayList<TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer>();
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
     * @return the endOfDayInventory
     */
    public List<TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer>
            getEndOfDayInventory() {
	return endOfDayInventory;
    }

    /**
     * @param endOfDayInventory
     *            the endOfDayInventory to set
     */
    public void addInventory(
            TruckSalesFlatFileContainer.EndOfDayInventoryFlatFileContainer inventory) {
	endOfDayInventory.add(inventory);
    }

    /**
     * @return the inventoryRowNumber
     */
    public String getInventoryRowNumber() {
	return inventoryRowNumber;
    }

    /**
     * @param inventoryRowNumber
     *            the inventoryRowNumber to set
     */
    public void setInventoryRowNumber(String inventoryRowNumber) {
	this.inventoryRowNumber = inventoryRowNumber;
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

    public class EndOfDayInventoryFlatFileContainer
            extends AbstractFlatFileContainer {

	private String itemNumber;
	private String finalQuantity;

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
	 * @return the finalQuantity
	 */
	public String getFinalQuantity() {
	    return finalQuantity;
	}

	/**
	 * @param finalQuantity
	 *            the finalQuantity to set
	 */
	public void setFinalQuantity(String finalQuantity) {
	    this.finalQuantity = finalQuantity;
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
