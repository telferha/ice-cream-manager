package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "ROUTE")
public class Route extends EntitySupport {

    private static final long serialVersionUID = 4315982774076916956L;

    @Id
    @Column(name = "ROUTE_ID", updatable = false)
    private String routeId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "route")
    private List<RouteInstance> routeInstances;

    public String getRouteId() {
	return routeId;
    }

    public void setRouteId(String routeId) {
	this.routeId = routeId;
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
