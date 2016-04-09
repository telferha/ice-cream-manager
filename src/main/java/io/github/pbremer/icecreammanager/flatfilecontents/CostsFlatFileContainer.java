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
public class CostsFlatFileContainer extends AbstractFlatFileContainer {

    private String truckNumber;
    private String gasSpent;
    private String hoursOut;
    private List<CostsFlatFileContainer.InventoryLossFlatFileContainer> lostInventory;
    private String lostCount;

    public CostsFlatFileContainer() {
	lostInventory =
	        new ArrayList<CostsFlatFileContainer.InventoryLossFlatFileContainer>();
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
     * @return the gasSpent
     */
    public String getGasSpent() {
	return gasSpent;
    }

    /**
     * @param gasSpent
     *            the gasSpent to set
     */
    public void setGasSpent(String gasSpent) {
	this.gasSpent = gasSpent;
    }

    /**
     * @return the hoursOut
     */
    public String getHoursOut() {
	return hoursOut;
    }

    /**
     * @param hoursOut
     *            the hoursOut to set
     */
    public void setHoursOut(String hoursOut) {
	this.hoursOut = hoursOut;
    }

    /**
     * @return the lostInventory
     */
    public List<CostsFlatFileContainer.InventoryLossFlatFileContainer>
            getLostInventory() {
	return lostInventory;
    }

    /**
     * @param lostInventory
     *            the lostInventory to set
     */
    public void setLostInventory(
            List<CostsFlatFileContainer.InventoryLossFlatFileContainer> lostInventory) {
	this.lostInventory = lostInventory;
    }

    public void addLoss(InventoryLossFlatFileContainer inventoryLoss) {
	lostInventory.add(inventoryLoss);
    }

    /**
     * @return the lostCount
     */
    public String getLostCount() {
	return lostCount;
    }

    /**
     * @param lostCount
     *            the lostCount to set
     */
    public void setLostCount(String lostCount) {
	this.lostCount = lostCount;
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

    public class InventoryLossFlatFileContainer
            extends AbstractFlatFileContainer {

	private String itemNumber;
	private String quantityLost;

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
	 * @return the quantityLost
	 */
	public String getQuantityLost() {
	    return quantityLost;
	}

	/**
	 * @param quantityLost
	 *            the quantityLost to set
	 */
	public void setQuantityLost(String quantityLost) {
	    this.quantityLost = quantityLost;
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
