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
import javax.persistence.OneToOne;
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
@Table(name = "DRIVER_INSTANCE", uniqueConstraints = @UniqueConstraint(
        columnNames = { "DRIVER_DAY", "DRIVER_ID" }))
@JsonInclude(Include.NON_EMPTY)
public class DriverInstance extends EntitySupport {

    private static final long serialVersionUID = -5196627091470377041L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DRIVER_INSTANCE_ID")
    private long driverInstanceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "DRIVER_DAY")
    private Date driverDay;

    @OneToOne
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "truckInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private TruckInstance truckInstance;

    @ManyToOne
    @JoinColumn(name = "DRIVER_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "driverId")
    @JsonIdentityReference(alwaysAsId = true)
    private Driver driver;

    @Column(name = "HOURS")
    private BigDecimal hours;

    public long getDriverInstanceId() {
	return driverInstanceId;
    }

    public Date getDriverDay() {
	return driverDay;
    }

    public void setDriverDay(Date driverDay) {
	this.driverDay = driverDay;
    }

    public TruckInstance getTruckInstance() {
	return truckInstance;
    }

    public void setTruckInstance(TruckInstance truckInstance) {
	this.truckInstance = truckInstance;
    }

    public Driver getDriver() {
	return driver;
    }

    public void setDriver(Driver driver) {
	this.driver = driver;
    }

    public BigDecimal getHours() {
	return hours;
    }

    public void setHours(BigDecimal hours) {
	this.hours = hours;
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
