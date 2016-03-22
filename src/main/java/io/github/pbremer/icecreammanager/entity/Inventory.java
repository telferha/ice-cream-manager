package io.github.pbremer.icecreammanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class Inventory extends EntitySupport {

    private static final long serialVersionUID = 1440448106184050947L;

    @Column(name = "INVENTORY_DAY")
    @Temporal(TemporalType.DATE)
    private Date inventoryDay;

    @ManyToOne
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    private TruckInstance truckInstance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ICE_CREAM_ID")
    private IceCream iceCream;

    @Column(name = "AMMOUNT")
    private int ammount;

    public Inventory() {
	ammount = 0;
    }

    public Date getInventoryDay() {
	return inventoryDay;
    }

    public void setInventoryDay(Date inventoryDay) {
	this.inventoryDay = inventoryDay;
    }

    public TruckInstance getTruckInstance() {
	return truckInstance;
    }

    public void setTruckInstance(TruckInstance truckInstance) {
	this.truckInstance = truckInstance;
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
