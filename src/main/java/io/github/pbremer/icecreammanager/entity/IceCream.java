package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "ICE_CREAM")
@JsonInclude(Include.NON_EMPTY)
public class IceCream extends ActivatableEntitySupport {

    private static final long serialVersionUID = 2908680501990930010L;

    @Id
    @Column(name = "ICE_CREAM_NAME", updatable = false)
    private String iceCreamName;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "iceCream")
    private List<IceCreamInstance> iceCreamInstances;

    @Column(name = "CURRENT_SUPPLIER_PRICE")
    private BigDecimal currentSupplierPrice;

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

    public String getIceCreamName() {
	return iceCreamName;
    }

    public void setIceCreamName(String iceCreamName) {
	this.iceCreamName = iceCreamName;
    }

    public List<IceCreamInstance> getIceCreamInstances() {
	return iceCreamInstances;
    }

    public void setIceCreamInstances(List<IceCreamInstance> iceCreamInstances) {
	this.iceCreamInstances = iceCreamInstances;
    }

    /**
     * @return the currentSupplierPrice
     */
    public BigDecimal getCurrentSupplierPrice() {
	return currentSupplierPrice;
    }

    /**
     * @param currentSupplierPrice
     *            the currentSupplierPrice to set
     */
    public void setCurrentSupplierPrice(BigDecimal currentSupplierPrice) {
	this.currentSupplierPrice = currentSupplierPrice;
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
