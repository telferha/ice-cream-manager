package io.github.pbremer.icecreammanager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class Inventory extends InstanceEntitySupport {

    private static final long serialVersionUID = 1440448106184050947L;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ICE_CREAM_INSTANCE_ID")
    private IceCreamInstance iceCreamInstance;

    @Column(name = "AMMOUNT")
    private int ammount = 0;

    public IceCreamInstance getIceCreamInstance() {
	return iceCreamInstance;
    }

    public void setIceCreamInstance(IceCreamInstance iceCreamInstance) {
	this.iceCreamInstance = iceCreamInstance;
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
