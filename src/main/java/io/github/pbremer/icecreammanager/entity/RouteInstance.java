package io.github.pbremer.icecreammanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ROUTE_INSTANCE")
public class RouteInstance extends EntitySupport {

    private static final long serialVersionUID = 6686558272033820280L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTE_INSTANCE_ID")
    private long routeInstanceId;

    @Column(name = "ROUTE_DAY")
    @Temporal(TemporalType.DATE)
    private Date routeDay;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @ManyToMany
    @JoinTable(name = "ROUTE_ZONES",
            joinColumns = @JoinColumn(name = "ROUTE_INSTANCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ZONE_NAME"))
    private List<Zone> zones;

    @OneToOne
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    private TruckInstance truckInstance;

    public long getRouteInstanceId() {
	return routeInstanceId;
    }

    public Date getRouteDay() {
	return routeDay;
    }

    public void setRouteDay(Date routeDay) {
	this.routeDay = routeDay;
    }

    public Route getRoute() {
	return route;
    }

    public void setRoute(Route route) {
	this.route = route;
    }

    public List<Zone> getZones() {
	return zones;
    }

    public void setZones(List<Zone> zones) {
	this.zones = zones;
    }

    public TruckInstance getTruckInstance() {
	return truckInstance;
    }

    public void setTruckInstance(TruckInstance truckInstance) {
	this.truckInstance = truckInstance;
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
