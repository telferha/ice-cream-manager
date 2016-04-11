/**
 * 
 */
package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "WAREHOUSE_INVENTORY_ID", uniqueConstraints = @UniqueConstraint(
        columnNames = { "DAY", "ICE_CREAM_INSTANCE_ID" }))
@JsonInclude(Include.NON_EMPTY)
public class WarehouseInventory extends Inventory {

    private static final long serialVersionUID = -8912697085611687702L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WAREHOUSE_INVENTORY_ID")
    private long warehouseInventoryId;

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
