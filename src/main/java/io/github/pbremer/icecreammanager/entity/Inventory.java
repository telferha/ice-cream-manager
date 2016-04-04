package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
public abstract class Inventory extends InstanceEntitySupport {

    private static final long serialVersionUID = 1440448106184050947L;

    @ManyToOne
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "truckInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private TruckInstance truckInstance;

    @Column(name = "SALES_PRICE")
    private BigDecimal salesPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ICE_CREAM_INSTANCE_ID")
    private IceCreamInstance iceCreamInstance;

    @Column(name = "AMMOUNT")
    private int ammount;

    public Inventory() {
	ammount = 0;
    }

    public TruckInstance getTruckInstance() {
	return truckInstance;
    }

    public void setTruckInstance(TruckInstance truckInstance) {
	this.truckInstance = truckInstance;
    }

    public IceCreamInstance getIceCreamInstance() {
	return iceCreamInstance;
    }

    public void setIceCreamInstance(IceCreamInstance iceCreamInstance) {
	this.iceCreamInstance = iceCreamInstance;
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
