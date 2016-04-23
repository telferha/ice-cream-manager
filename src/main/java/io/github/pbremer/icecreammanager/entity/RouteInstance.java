package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ROUTE_INSTANCE", uniqueConstraints = @UniqueConstraint(
        columnNames = { "DAY", "ROUTE_ID" }))
@JsonInclude(Include.NON_EMPTY)
public class RouteInstance extends InstanceEntitySupport {

    private static final long serialVersionUID = 6686558272033820280L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTE_INSTANCE_ID")
    private long routeInstanceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "routeId")
    @JsonIdentityReference(alwaysAsId = true)
    private Route route;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ROUTE_ZONES",
            joinColumns = @JoinColumn(name = "ROUTE_INSTANCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ZONE_NAME"))
    @JsonIgnoreProperties({ "routeInstances", "createdDate",
            "lastModifiedDate" })
    private List<Zone> zones;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "truckInstanceId")
    @JsonIdentityReference(alwaysAsId = true)
    private TruckInstance truckInstance;

    @SuppressWarnings("unused")
    private transient long truckInstanceId;

    public void setTruckInstanceId(long truckInstanceId) {
	this.truckInstanceId = truckInstanceId;
    }

    public long getRouteInstanceId() {
	return routeInstanceId;
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
