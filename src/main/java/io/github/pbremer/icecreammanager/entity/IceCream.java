package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "ICE_CREAM")
public class IceCream extends EntitySupport {

    private static final long serialVersionUID = 2908680501990930010L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ICE_CREAM_ID", updatable = false)
    private long iceCreamId;

    @Column(name = "ICE_CREAM_NAME")
    private String iceCreamName;

    @Column(name = "SALES_PRICE")
    private BigDecimal salesPrice;

    @Column(name = "SUPPLIER_PRICE")
    private BigDecimal supplierPrice;

    @Column(name = "DAY", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date day;

    public long getIceCreamId() {
	return iceCreamId;
    }

    public void setIceCreamId(long iceCreamId) {
	this.iceCreamId = iceCreamId;
    }

    public String getIceCreamName() {
	return iceCreamName;
    }

    public void setIceCreamName(String iceCreamName) {
	this.iceCreamName = iceCreamName;
    }

    public BigDecimal getSalesPrice() {
	return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
	this.salesPrice = salesPrice;
    }

    public BigDecimal getSupplierPrice() {
	return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
	this.supplierPrice = supplierPrice;
    }

    public Date getDay() {
	return day;
    }

    public void setDay(Date day) {
	this.day = day;
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
