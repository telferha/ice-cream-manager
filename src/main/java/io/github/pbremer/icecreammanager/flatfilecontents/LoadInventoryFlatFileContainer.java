/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class LoadInventoryFlatFileContainer extends AbstractFlatFileContainer {

    private String itemNumber;
    private String wareHouseQuantity;
    private String price;
    private String description;

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
     * @return the wareHouseQuantity
     */
    public String getWareHouseQuantity() {
	return wareHouseQuantity;
    }

    /**
     * @param wareHouseQuantity
     *            the wareHouseQuantity to set
     */
    public void setWareHouseQuantity(String wareHouseQuantity) {
	this.wareHouseQuantity = wareHouseQuantity;
    }

    /**
     * @return the price
     */
    public String getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(String price) {
	this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
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
