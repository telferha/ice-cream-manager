package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ZONE", uniqueConstraints = @UniqueConstraint(
        columnNames = { "CITY_NAME", "ZONE_NAME" }))
@JsonInclude(Include.NON_EMPTY)
public class Zone extends ActivatableEntitySupport {

    private static final long serialVersionUID = 1497610113204826980L;

    @Id
    @Column(name = "ZONE_NAME", updatable = false)
    private String zoneName;

    @ManyToOne
    @JoinColumn(name = "CITY_NAME")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "cityName")
    @JsonIdentityReference(alwaysAsId = true)
    private City city;

    @ManyToMany
    @JoinTable(name = "ROUTE_ZONES",
            inverseJoinColumns = @JoinColumn(name = "ROUTE_INSTANCE_ID"),
            joinColumns = @JoinColumn(name = "ZONE_NAME"))
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<RouteInstance> routeInstances;

    public String getZoneName() {
	return zoneName;
    }

    public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
    }

    public City getCity() {
	return city;
    }

    public void setCity(City city) {
	this.city = city;
    }

    public List<RouteInstance> getRouteInstances() {
	return routeInstances;
    }

    public void setRouteInstances(List<RouteInstance> routeInstances) {
	this.routeInstances = routeInstances;
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
