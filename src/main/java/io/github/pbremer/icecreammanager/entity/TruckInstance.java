package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
        columnNames = { "DAY", "TRUCK_NUMBER" }))
@JsonInclude(Include.NON_EMPTY)
public class TruckInstance extends InstanceEntitySupport {

    private static final long serialVersionUID = 7969346198180317639L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRUCK_INSTANCE_ID")
    private long truckInstanceId;

    @ManyToOne
    @JoinColumn(name = "TRUCK_NUMBER")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "truckNumber")
    @JsonIdentityReference(alwaysAsId = true)
    private Truck truck;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<BeginDayInventory> beginDayInventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<EndDayInventory> endDayInventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<InventoryLoss> inventoryLoss;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROUTE_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "routeInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private RouteInstance routeInstance;

    @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "DRIVER_INSTANCE_ID")
    @PrimaryKeyJoinColumn
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "dirverInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private DriverInstance driverInstance;

    @Column(name = "HOURS_OUT")
    private BigDecimal hoursOut;

    @Column(name = "GAS_SPENT")
    private BigDecimal gasSpent;

    public long getTruckInstanceId() {
	return truckInstanceId;
    }

    public void setTruckInstanceId(long truckInstanceId) {
	this.truckInstanceId = truckInstanceId;
    }

    public Truck getTruck() {
	return truck;
    }

    public void setTruck(Truck truck) {
	this.truck = truck;
    }

    /**
     * @return the beginDayInventory
     */
    public List<BeginDayInventory> getBeginDayInventory() {
	return beginDayInventory;
    }

    /**
     * @param beginDayInventory
     *            the beginDayInventory to set
     */
    public void
            setBeginDayInventory(List<BeginDayInventory> beginDayInventory) {
	this.beginDayInventory = beginDayInventory;
    }

    /**
     * @return the endDayInventory
     */
    public List<EndDayInventory> getEndDayInventory() {
	return endDayInventory;
    }

    /**
     * @param endDayInventory
     *            the endDayInventory to set
     */
    public void setEndDayInventory(List<EndDayInventory> endDayInventory) {
	this.endDayInventory = endDayInventory;
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

    /**
     * @return the hoursOut
     */
    public BigDecimal getHoursOut() {
	return hoursOut;
    }

    /**
     * @param hoursOut
     *            the hoursOut to set
     */
    public void setHoursOut(BigDecimal hoursOut) {
	this.hoursOut = hoursOut;
    }

    /**
     * @return the gasSpent
     */
    public BigDecimal getGasSpent() {
	return gasSpent;
    }

    /**
     * @param gasSpent
     *            the gasSpent to set
     */
    public void setGasSpent(BigDecimal gasSpent) {
	this.gasSpent = gasSpent;
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
