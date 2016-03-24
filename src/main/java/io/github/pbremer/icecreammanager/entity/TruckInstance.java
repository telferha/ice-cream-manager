package io.github.pbremer.icecreammanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "TRUCK_INSTANCE", uniqueConstraints = @UniqueConstraint(
        columnNames = { "TRUCK_DAY", "TRUCK_NUMBER" }))
@JsonInclude(Include.NON_EMPTY)
public class TruckInstance extends EntitySupport {

    private static final long serialVersionUID = 7969346198180317639L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRUCK_INSTANCE_ID")
    private long truckInstanceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRUCK_DAY")
    private Date truckDay;

    @ManyToOne
    @JoinColumn(name = "TRUCK_NUMBER")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "truckNumber")
    @JsonIdentityReference(alwaysAsId = true)
    private Truck truck;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<TruckInventory> inventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<InventoryLoss> inventoryLoss;

    @OneToOne
    @JoinColumn(name = "ROUTE_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "routeInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private RouteInstance routeInstance;

    @OneToOne
    @JoinColumn(name = "DRIVER_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "dirverInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private DriverInstance driverInstance;

    public long getTruckInstanceId() {
	return truckInstanceId;
    }

    public Date getTruckDay() {
	return truckDay;
    }

    public void setTruckDay(Date truckDay) {
	this.truckDay = truckDay;
    }

    public Truck getTruck() {
	return truck;
    }

    public void setTruck(Truck truck) {
	this.truck = truck;
    }

    public List<TruckInventory> getInventory() {
	return inventory;
    }

    public void setInventory(List<TruckInventory> inventory) {
	this.inventory = inventory;
    }

    public List<InventoryLoss> getInventoryLoss() {
	return inventoryLoss;
    }

    public void setInventoryLoss(List<InventoryLoss> inventoryLoss) {
	this.inventoryLoss = inventoryLoss;
    }

    public RouteInstance getRouteInstance() {
	return routeInstance;
    }

    public void setRouteInstance(RouteInstance routeInstance) {
	this.routeInstance = routeInstance;
    }

    public DriverInstance getDriverInstance() {
	return driverInstance;
    }

    public void setDriverInstance(DriverInstance driverInstance) {
	this.driverInstance = driverInstance;
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
