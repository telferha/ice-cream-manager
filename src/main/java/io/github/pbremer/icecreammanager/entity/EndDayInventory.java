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
@Table(name = "END_DAY_INVENTORY", uniqueConstraints = @UniqueConstraint(
        columnNames = { "DAY", "TRUCK_INSTANCE_ID", "ICE_CREAM_INSTANCE_ID" }))
@JsonInclude(Include.NON_EMPTY)
public class EndDayInventory extends SellableInventory {

    private static final long serialVersionUID = 8251593985434934794L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "END_DAY_INVENTORY_ID")
    private long id;

    public long getId() {
	return id;
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
