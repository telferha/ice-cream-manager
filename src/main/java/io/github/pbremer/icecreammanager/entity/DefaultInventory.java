/**
 * 
 */
package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Patrick Bremer
 */
@Entity
@Table(name = "DEFAULT_INVENTORY")
@JsonInclude(Include.NON_EMPTY)
public class DefaultInventory extends ActivatableEntitySupport {

    private static final long serialVersionUID = -2784056390698586473L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEFAULT_INVENTORY_ID")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ICE_CREAM_NAME")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "iceCreamName")
    @JsonIdentityReference(alwaysAsId = true)
    private IceCream iceCream;

    @Column(name = "AMMOUNT")
    private int ammount = 0;

    public long getId() {
	return id;
    }

    public IceCream getIceCream() {
	return iceCream;
    }

    public void setIceCream(IceCream iceCream) {
	this.iceCream = iceCream;
    }

    public int getAmmount() {
	return ammount;
    }

    public void setAmmount(int ammount) {
	this.ammount = ammount;
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
