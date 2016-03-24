package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ICE_CREAM_INSTANCE", uniqueConstraints = @UniqueConstraint(
        columnNames = { "ICE_CREAM_DAY", "ICE_CREAM_NAME" }))
@JsonInclude(Include.NON_EMPTY)
public class IceCreamInstance extends EntitySupport {

    private static final long serialVersionUID = -3761916152395485693L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ICE_CREAM_INSTANCE_ID")
    private long iceCreamInstanceId;

    @Column(name = "SALES_PRICE")
    private BigDecimal salesPrice;

    @Column(name = "SUPPLIER_PRICE")
    private BigDecimal supplierPrice;

    @Column(name = "ICE_CREAM_DAY", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date iceCreamDay;

    @ManyToOne
    @JoinColumn(name = "ICE_CREAM_NAME")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "iceCreamName")
    @JsonIdentityReference(alwaysAsId = true)
    private IceCream iceCream;

    public long getIceCreamInstanceId() {
	return iceCreamInstanceId;
    }

    public void setIceCreamInstanceId(long iceCreamInstanceId) {
	this.iceCreamInstanceId = iceCreamInstanceId;
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

    public Date getIceCreamDay() {
	return iceCreamDay;
    }

    public void setIceCreamDay(Date iceCreamDay) {
	this.iceCreamDay = iceCreamDay;
    }

    public IceCream getIceCream() {
	return iceCream;
    }

    public void setIceCream(IceCream iceCream) {
	this.iceCream = iceCream;
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
