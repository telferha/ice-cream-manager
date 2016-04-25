/**
 * 
 */
package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Patrick Bremer
 */
@Entity
@Table(name = "WAREHOUSE_INVENTORY", uniqueConstraints = @UniqueConstraint(
        columnNames = { "ICE_CREAM_NAME" }))
@JsonInclude(Include.NON_EMPTY)
public class WarehouseInventory extends ActivatableEntitySupport {

    private static final long serialVersionUID = -8912697085611687702L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WAREHOUSE_INVENTORY_ID")
    private long warehouseInventoryId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ICE_CREAM_NAME")
    private IceCream iceCream;

    @Column(name = "DEFAULT_SALES_PRICE")
    private BigDecimal salesPrice;

    @Column(name = "CURRENT_QUANTITY")
    private long quantity;

    /**
     * @return the iceCream
     */
    public IceCream getIceCream() {
	return iceCream;
    }

    /**
     * @return the salesPrice
     */
    public BigDecimal getSalesPrice() {
	return salesPrice;
    }

    /**
     * @param salesPrice
     *            the salesPrice to set
     */
    public void setSalesPrice(BigDecimal salesPrice) {
	this.salesPrice = salesPrice;
    }

    /**
     * @return the quantity
     */
    public long getQuantity() {
	return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(long quantity) {
	this.quantity = quantity;
    }

    /**
     * @param iceCream
     *            the iceCream to set
     */
    public void setIceCream(IceCream iceCream) {
	this.iceCream = iceCream;
    }

    public long getId() {
	return warehouseInventoryId;
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
