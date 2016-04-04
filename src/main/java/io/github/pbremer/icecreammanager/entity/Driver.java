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
@Table(name = "DRIVER")
@JsonInclude(Include.NON_EMPTY)
public class Driver extends ActivatableEntitySupport {

    private static final long serialVersionUID = -4384329683864675619L;

    @Id
    @Column(name = "DRIVER_ID", updatable = false)
    private String driverId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "driver")
    private List<DriverInstance> driverInstances;

    @Column(name = "WAGE")
    private BigDecimal wage;

    public String getDriverId() {
	return driverId;
    }

    public void setDriverId(String driverId) {
	this.driverId = driverId;
    }

    public List<DriverInstance> getDriverInstances() {
	return driverInstances;
    }

    public void setDriverInstances(List<DriverInstance> driverInstances) {
	this.driverInstances = driverInstances;
    }

    public BigDecimal getWage() {
	return wage;
    }

    public void setWage(BigDecimal wage) {
	this.wage = wage;
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
